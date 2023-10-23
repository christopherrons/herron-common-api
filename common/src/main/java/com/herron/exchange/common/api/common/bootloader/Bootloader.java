package com.herron.exchange.common.api.common.bootloader;

import com.herron.exchange.common.api.common.enums.BootloaderStatus;
import com.herron.exchange.common.api.common.wrappers.ThreadWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.herron.exchange.common.api.common.enums.BootloaderStatus.INITIALIZED;
import static com.herron.exchange.common.api.common.enums.BootloaderStatus.UNDEFINED;

public abstract class Bootloader {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String id;
    private final CountDownLatch countDownLatch;
    private ExecutorService service;
    protected BootloaderStatus bootloaderStatus = UNDEFINED;

    protected Bootloader(String id, CountDownLatch countDownLatch) {
        this.id = id;
        this.countDownLatch = countDownLatch;
        this.service = Executors.newSingleThreadExecutor(new ThreadWrapper(id));
    }

    protected abstract void bootloaderInit();

    protected abstract void bootloaderComplete();

    public void init() {
        service.execute(this::run);
    }

    public void run() {
        bootloaderStatus = INITIALIZED;
        bootloaderInit();
    }

    public void await() {
        logger.info("Waiting for bootloader {}", id);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {

        }
        logger.info("Done waiting for bootloader {}", id);
    }


}
