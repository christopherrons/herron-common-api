package com.herron.exchange.common.api.common.messages;

import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.enums.DataLoadingStateEnum;
import com.herron.exchange.common.api.common.logging.EventLogger;
import com.herron.exchange.common.api.common.messages.common.HerronDataLoadingState;
import com.herron.exchange.common.api.common.messages.common.ImmutableHerronDataLoadingState;
import com.herron.exchange.common.api.common.model.PartitionKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public abstract class KafkaBroadcastProducer {

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
        HerronDataLoadingState start = ImmutableHerronDataLoadingState.builder().timeOfEventMs(Instant.now().toEpochMilli())
                .state(DataLoadingStateEnum.START)
                .build();
        broadcastMessage(start);
        logger.info("Broadcasting started for partition {}", partitionKey);
    }

    public void endBroadcasting() {
        HerronDataLoadingState done = ImmutableHerronDataLoadingState.builder().timeOfEventMs(Instant.now().toEpochMilli())
                .state(DataLoadingStateEnum.DONE)
                .build();
        broadcastMessage(done);
        isBroadCasting.set(false);
        logger.info("Broadcasting stopped for partition {}. Total messages {}.", partitionKey, eventLogger.totalNrOfEvents());
    }

    public synchronized boolean broadcastMessage(Message message) {
        if (!isBroadCasting.get() || message == null) {
            return false;
        }

        eventLogger.logEvent();
        var broadCast = ImmutableHerronBroadcastMessage.builder()
                .message(message)
                .messageMessageType(message.messageType().getMessageTypeId())
                .sequenceNumber(sequenceNumberHandler.getAndIncrement())
                .timeOfEventMs(Instant.now().toEpochMilli())
                .build();

        kafkaTemplate.send(partitionKey.topicEnum().getTopicName(), partitionKey.partitionId(), broadCast.messageType().getMessageTypeId(), broadCast);
        return true;
    }
}
