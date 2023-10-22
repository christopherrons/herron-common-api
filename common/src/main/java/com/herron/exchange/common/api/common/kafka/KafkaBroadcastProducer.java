package com.herron.exchange.common.api.common.kafka;

import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.enums.DataStreamEnum;
import com.herron.exchange.common.api.common.enums.EventType;
import com.herron.exchange.common.api.common.logging.EventLogger;
import com.herron.exchange.common.api.common.messages.ImmutableBroadcastMessage;
import com.herron.exchange.common.api.common.messages.common.DataStreamState;
import com.herron.exchange.common.api.common.messages.common.ImmutableDataStreamState;
import com.herron.exchange.common.api.common.messages.common.PartitionKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import static com.herron.exchange.common.api.common.enums.DataStreamEnum.DONE;
import static com.herron.exchange.common.api.common.enums.DataStreamEnum.START;
import static com.herron.exchange.common.api.common.enums.EventType.SYSTEM;

public class KafkaBroadcastProducer {

    private final Logger logger = LoggerFactory.getLogger(KafkaBroadcastProducer.class);
    private final PartitionKey partitionKey;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final AtomicLong sequenceNumberHandler = new AtomicLong(1);
    private final EventLogger eventLogger;
    private final AtomicBoolean isBroadCasting = new AtomicBoolean(false);

    protected KafkaBroadcastProducer(PartitionKey partitionKey, KafkaTemplate<String, Object> kafkaTemplate, EventLogger eventLogger) {
        this.partitionKey = partitionKey;
        this.kafkaTemplate = kafkaTemplate;
        this.eventLogger = eventLogger;
    }

    public void startBroadcasting() {
        isBroadCasting.set(true);
        DataStreamState start = ImmutableDataStreamState.builder().timeOfEventMs(Instant.now().toEpochMilli())
                .state(START)
                .eventType(SYSTEM)
                .build();
        broadcastMessage(start);
        logger.info("Broadcasting started for partition {}", partitionKey);
    }

    public void endBroadcasting() {
        DataStreamState done = ImmutableDataStreamState.builder().timeOfEventMs(Instant.now().toEpochMilli())
                .state(DONE)
                .eventType(SYSTEM)
                .build();
        broadcastMessage(done);
        isBroadCasting.set(false);
        logger.info("Broadcasting stopped for partition {}. Total messages {}.", partitionKey, eventLogger.totalNrOfEvents());
    }

    public synchronized boolean broadcastMessage(Message message) {
        if (!isBroadCasting.get() || message == null) {
            logger.warn("Attemping to broadcast message {} but broadcasting not started for {}.", message, partitionKey);
            return false;
        }

        eventLogger.logEvent();
        var broadCast = ImmutableBroadcastMessage.builder()
                .message(message)
                .sequenceNumber(sequenceNumberHandler.getAndIncrement())
                .timeOfEventMs(Instant.now().toEpochMilli())
                .partitionKey(partitionKey)
                .build();

        kafkaTemplate.send(partitionKey.topicEnum().getTopicName(), partitionKey.partitionId(), broadCast.messageType().getMessageTypeId(), broadCast);
        return true;
    }
}
