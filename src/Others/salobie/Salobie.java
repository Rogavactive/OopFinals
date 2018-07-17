package com.salobie;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Salobie     {
    List in;
    List out;

    public static void main(String[] args) {

        final Salobie r = new Salobie();
        r.in = Collections.synchronizedList(new LinkedList<>()); // შემავალი
        // სინი
        r.out = Collections.synchronizedList(new LinkedList()); // გამომავალი


        // სინი

        // მზარეულების დასტარტვა
        Mzareuli c1 = new Mzareuli(10, r), c2 = new Mzareuli(15, r), c3 = new Mzareuli(7, r);
        c1.start();
        c2.start();
        c3.start();

        // ეს ნაკადი იღებს შეკვეთებს და დებს შემავალ სინზე
        (new Thread() {
            public void run() {
                while (true) {
                    Random rand = new Random();
                    try {
                        Thread.sleep(Math.abs(rand.nextInt()) % 1000);
                    } catch (InterruptedException e) {
                    }
                    r.in.add(new Lobio(r));
                    synchronized (r.in) {
                        r.in.notifyAll();
                    }
                } // end while
            } // end run()
        }).start();

        // ეს ნაკადი იღებს ლობიოს გამომავალი სინიდან
        // და აძლევს კლიენტებს
        (new Thread() {
            public void run() {
                while (true) {
                    while (r.out.size() == 0) {
                        try {
                            synchronized (r.out) {
                                r.out.wait();
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                    // ლობიოს აღება
                    r.out.remove(0);
                } // end while
            } // end run()
        }).start();
    }//end main
} //end Salobie
