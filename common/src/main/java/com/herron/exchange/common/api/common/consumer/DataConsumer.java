package com.herron.exchange.common.api.common.consumer;

import com.herron.exchange.common.api.common.enums.DataStreamEnum;
import com.herron.exchange.common.api.common.wrappers.ThreadWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.herron.exchange.common.api.common.enums.DataStreamEnum.DONE;
import static com.herron.exchange.common.api.common.enums.DataStreamEnum.START;


public abstract class DataConsumer {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String id;
    protected final CountDownLatch countDownLatch;
    private final ExecutorService service;
    protected DataStreamEnum consumerStatus;

    protected DataConsumer(String id, CountDownLatch countDownLatch) {
        this.id = id;
        this.countDownLatch = countDownLatch;
        this.service = Executors.newSingleThreadExecutor(new ThreadWrapper(id + "-" + "CONSUMER"));
    }

    protected abstract void consumerInit();

    protected void consumerComplete() {
        logger.info("Consumer {} done.", id);
        consumerStatus = DONE;
        shutdown();
    }

    public void init() {
        service.execute(this::run);
    }

    public void run() {
        logger.info("Consumer {} started", id);
        consumerStatus = START;
        consumerInit();
    }

    public void await() {
        logger.info("Waiting for consumer {} to complete.", id);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {

        }
        logger.info("Finished waiting for consumer {} which has completed.", id);
    }

    protected void shutdown() {
        service.shutdown();
        logger.info("Consumer {} shutdown", id);
    }

}
