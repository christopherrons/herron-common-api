package com.herron.exchange.common.api.common.kafka;

import com.herron.exchange.common.api.common.logging.EventLogger;
import com.herron.exchange.common.api.common.wrappers.ThreadWrapper;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class KafkaTopicSubscription {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Consumer<String, String> consumer;
    private final AtomicBoolean isConsume = new AtomicBoolean(false);
    private final EventLogger eventLogger;
    private final ExecutorService service;

    protected KafkaTopicSubscription(Consumer<String, String> consumer, EventLogger eventLogger) {
        this.consumer = consumer;
        this.eventLogger = eventLogger;
        this.service = Executors.newSingleThreadExecutor(new ThreadWrapper(consumer.subscription().toString()));
    }

    protected abstract void handleEvent(ConsumerRecord<String, String> recordItem);

    public void run() {
        isConsume.set(true);
        service.execute(this::consume);
        logger.info("Started kafka subscription {}.", consumer.subscription());
    }

    public void stop() {
        logger.info("Stopping kafka subscription {}.", consumer.subscription());
        isConsume.set(false);
        service.shutdown();
    }

    private void consume() {
        while (isConsume.get()) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> recordItem : records) {
                eventLogger.logEvent();
                handleEvent(recordItem);
            }
        }
    }
}
