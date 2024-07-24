package com.herron.exchange.common.api.common.kafka;

import com.herron.exchange.common.api.common.kafka.model.KafkaSubscriptionDetails;
import com.herron.exchange.common.api.common.logging.EventLogger;
import com.herron.exchange.common.api.common.wrappers.ThreadWrapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.ConsumerFactory;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class KafkaTopicSubscription {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final AtomicBoolean isConsume = new AtomicBoolean(false);
    private final EventLogger eventLogger;
    private final ExecutorService service;
    private final KafkaSubscriptionDetails details;

    protected KafkaTopicSubscription(KafkaSubscriptionDetails details) {
        this.details = details;
        this.eventLogger = new EventLogger("", logger, details.eventLoggingRate());
        this.service = Executors.newSingleThreadExecutor(new ThreadWrapper("KAFKA-SUB" + "-" + details.partitionKey()));
    }

    protected abstract void handleEvent(ConsumerRecord<String, String> recordItem);

    public void run(ConsumerFactory<String, String> consumerFactory, TopicPartition topicPartition) {
        isConsume.set(true);
        service.execute(() -> consume(consumerFactory, topicPartition));
    }

    public void stop() {
        isConsume.set(false);
    }

    private void consume(ConsumerFactory<String, String> consumerFactory, TopicPartition topicPartition) {
        logger.info("Started kafka subscription {}.", details.partitionKey());
        try (var consumer = consumerFactory.createConsumer(details.groupId())) {
            consumer.assign(List.of(topicPartition));

            if (details.offset() != null) {
                consumer.seek(topicPartition, details.offset());
            }

            while (isConsume.get()) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> recordItem : records) {
                    eventLogger.logEvent();
                    handleEvent(recordItem);
                }
            }

        } catch (Exception e) {
            logger.error("Error while creating consumer.", e);
        } finally {
            logger.info("Stopping kafka subscription {}, total events consumed {}.", details.partitionKey(), eventLogger.getTotalNrOfEvents());
            service.shutdown();
        }
    }
}
