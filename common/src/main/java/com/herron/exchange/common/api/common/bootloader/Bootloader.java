package com.herron.exchange.common.api.common.bootloader;

import com.herron.exchange.common.api.common.enums.BootloaderStatus;
import com.herron.exchange.common.api.common.wrappers.ThreadWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.herron.exchange.common.api.common.enums.BootloaderStatus.*;

public abstract class Bootloader {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String id;
    private final ExecutorService service;
    protected BootloaderStatus bootloaderStatus = UNDEFINED;

    protected Bootloader(String id) {
        this.id = id;
        this.service = Executors.newSingleThreadExecutor(new ThreadWrapper(id + "-" + "BOOTLOADER"));
    }

    protected abstract void bootloaderInit();

    protected void bootloaderComplete() {
        logger.info("Bootloader {} done.", id);
        bootloaderStatus = COMPLETE;
        shutdown();
    }

    public void init() {
        service.execute(this::run);
    }

    private void run() {
        logger.info("Bootloader {} started.", id);
        bootloaderStatus = INITIALIZING;
        bootloaderInit();
    }

    protected void shutdown() {
        service.shutdown();
        logger.info("Bootloader {} shutdown.", id);
    }
}
