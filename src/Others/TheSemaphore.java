import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class TheSemaphore {
    int size;
    boolean isFear;

    public TheSemaphore(){
        this(1,true);
    }

    public TheSemaphore(int c,boolean isFear){
        size=c;
        this.isFear = isFear;
    }

    public void release(){

    }

    public void acquire(){
    }

}
