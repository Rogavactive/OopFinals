package com.ProducerConsumer;

import java.util.Random;

public class Consumer extends Thread {
    private Random r = new Random();
    private Buffer buffer;
    public Consumer(Buffer b) {
        buffer = b;
    }

    public void run() {
        int value, bin;
        while (true) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bin = r.nextInt(buffer.getNumBins()); //იღებს ბუფერის ნომერს (0..numBins-1)
            value = buffer.get(bin);
            if(value!=-1)
                System.out.println(bin + " - consumer - " + value);
        }
    }

}
