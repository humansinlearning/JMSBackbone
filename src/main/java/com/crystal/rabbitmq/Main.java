package com.crystal.rabbitmq;

import java.util.HashMap;

public class Main {
    public Main() throws Exception{

        QueueConsumer consumer = new QueueConsumer("MyFirstCrystalQueue");
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

        Producer producer = new Producer("MyFirstCrystalQueue");

        for (int i = 0; i < 10; i++) {
            HashMap message = new HashMap();
            message.put("message number", i);
            producer.sendMessage(message);
            Thread.sleep(100);
            System.out.println("Message Number "+ i +" sent.");
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            new Main();
        } catch (Exception e) {
            System.err.println("Something went worng initializing producer/consumer connection to MQ broker");
            e.printStackTrace();
        }
    }
}
