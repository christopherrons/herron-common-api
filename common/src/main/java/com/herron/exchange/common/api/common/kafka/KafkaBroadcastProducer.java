package com.herron.exchange.common.api.common.kafka;

import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.logging.EventLogger;
import com.herron.exchange.common.api.common.messages.BroadcastMessage;
import com.herron.exchange.common.api.common.messages.ImmutableBroadcastMessage;
import com.herron.exchange.common.api.common.messages.common.DataStreamState;
import com.herron.exchange.common.api.common.messages.common.ImmutableDataStreamState;
import com.herron.exchange.common.api.common.messages.common.PartitionKey;
import com.herron.exchange.common.api.common.wrappers.ThreadWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.Instant;
import java.util.concurrent.*;
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
    private final BlockingQueue<BroadcastMessage> eventQueue = new LinkedBlockingDeque<>();
    private final ExecutorService service;

    protected KafkaBroadcastProducer(PartitionKey partitionKey, KafkaTemplate<String, Object> kafkaTemplate, EventLogger eventLogger) {
        this.partitionKey = partitionKey;
        this.kafkaTemplate = kafkaTemplate;
        this.eventLogger = eventLogger;
        this.service = Executors.newSingleThreadExecutor(new ThreadWrapper(partitionKey.toString()));
    }

    public void startBroadcasting() {
        isBroadCasting.set(true);
        DataStreamState start = ImmutableDataStreamState.builder().timeOfEventMs(Instant.now().toEpochMilli())
                .state(START)
                .eventType(SYSTEM)
                .build();
        broadcastMessage(start);
        logger.info("Broadcasting started for partition {}", partitionKey);
        service.execute(this::broadcastMessage);
    }

    public void endBroadcasting() {
        DataStreamState done = ImmutableDataStreamState.builder().timeOfEventMs(Instant.now().toEpochMilli())
                .state(DONE)
                .eventType(SYSTEM)
                .build();
        broadcastMessage(done);
        isBroadCasting.set(false);
        service.shutdown();
        logger.info("Broadcasting stopped for partition {}. Total messages {}.", partitionKey, eventLogger.totalNrOfEvents());
    }

    public synchronized boolean broadcastMessage(Message message) {
        if (!isBroadCasting.get() || message == null) {
            logger.warn("Attempting to broadcast message {} but broadcasting not started for {}.", message, partitionKey);
            return false;
        }

        var broadcast = ImmutableBroadcastMessage.builder()
                .message(message)
                .sequenceNumber(sequenceNumberHandler.getAndIncrement())
                .timeOfEventMs(Instant.now().toEpochMilli())
                .partitionKey(partitionKey)
                .eventType(SYSTEM)
                .build();

        eventQueue.add(broadcast);

        return true;
    }

    private void broadcastMessage() {
        BroadcastMessage broadCast;
        while (isBroadCasting.get() || !eventQueue.isEmpty()) {

            broadCast = poll();
            if (broadCast == null) {
                continue;
            }

            eventLogger.logEvent();
            kafkaTemplate.send(partitionKey.topicEnum().getTopicName(), partitionKey.partitionId(), broadCast.messageType().getMessageTypeId(), broadCast);
        }
    }

    private BroadcastMessage poll() {
        try {
            return eventQueue.poll(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            return null;
        }
    }
}
