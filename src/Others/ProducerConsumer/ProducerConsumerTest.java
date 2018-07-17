package com.ProducerConsumer;

public class ProducerConsumerTest {
    public static int numBins = 10;
    public static void main(String args[]) {
        Buffer b = new Buffer(numBins);
        Producer p = new Producer(b);
        Consumer c = new Consumer(b);
        p.start();
        c.start();
    }

}
