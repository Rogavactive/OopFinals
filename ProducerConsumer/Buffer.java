package ProducerConsumer;

import static java.lang.Thread.sleep;

public class Buffer {
    private class Semaphore {
        boolean filled;
        Semaphore (boolean b) {
            filled = b;
        }
    }

    private int seq[];
    private Semaphore available[];
    private int numBins;

    Buffer(int i) {
        numBins = i;
        seq = new int [numBins];
        available = new Semaphore [numBins];
        for (int j = 0; j < i; j++)
            available[j] = new Semaphore (false);
    }

    public int get(int bin) {
        int res = -1;
        synchronized (available[bin]){
            if(available[bin].filled){
                available[bin].filled=false;
                res = seq[bin];
                available[bin].notify();
            }
        }
        return res;
        // დაწერეთ თქვენი კოდი აქ
    }
    public void put(int value,int bin) {

        synchronized (available[bin]){
            try {
                while(available[bin].filled) {
                    available[bin].wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            seq[bin]=value;
            available[bin].filled=true;
        }
        // დაწერეთ თქვენი კოდი აქ
    }

    public int getNumBins(){
        return numBins;
    }

}
