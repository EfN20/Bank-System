package com.example.banksystem.filters;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class MyLogger implements Runnable {
    private Logger logger;
    private static FileHandler handler;
    private String message;

    static {
        try {
            handler = new FileHandler("/home/azatkali/JavaProjects/bank-system/src/main/webapp/WEB-INF/logger.log", true);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public MyLogger(String className, String message) {
        this.logger = Logger.getLogger(className);
        this.logger.setFilter(new MyFilter());
        this.logger.addHandler(handler);
        this.message = message;
    }


    @Override
    public void run() {
        logMessage(this.message);
    }

    private synchronized void logMessage(String message) {
        logger.info(message);
    }
}
