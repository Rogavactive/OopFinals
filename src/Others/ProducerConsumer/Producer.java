package Others.ProducerConsumer;

import java.util.Random;

public class Producer extends Thread{
    private Random r = new Random();
    private Buffer buffer;
    public Producer(Buffer b) {
        buffer = b;
    }

    public void run() {
        int i,j;
        while (true) {
            i = r.nextInt(100000); //იღებს ინფორმაციას იუზერისგან
            j = r.nextInt(buffer.getNumBins()); //იღებს ბუფერის ნომერს (0..numBins-1)
            buffer.put(i,j);
            System.out.println(j + " - producer - " + i);
        }
    }

}
