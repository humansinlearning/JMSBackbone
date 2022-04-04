package com.crystal.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class Main {

    private static final Logger logger
            = LoggerFactory.getLogger(Main.class);

    private static final int PRODUCER_SLEEP_TIME = 100;

    public Main() throws Exception{

        QueueConsumer consumer = new QueueConsumer("MyFirstCrystalQueue");
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();
        logger.debug("ConsumerThread starting...");

        Producer producer = new Producer("MyFirstCrystalQueue");

        for (int i = 0; i < 10; i++) {
            HashMap message = new HashMap();
            message.put("message number", i);
            producer.sendMessage(message);

            logger.debug("Producer sleeps for {} mil", PRODUCER_SLEEP_TIME);
            Thread.sleep(PRODUCER_SLEEP_TIME);

            logger.debug("Message Number {} sent. ", i);
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        logger.info("JMS Backbone is starting...");

        try {
            new Main();
        } catch (Exception e) {
            logger.error("Something went worng initializing producer/consumer connection to MQ broker", e);
        }

        logger.info("JMS Backbone is stoping...");
    }
}
