package Others;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class semaphore{
    private boolean isFear;
    private int c;
    Lock lock;
    Queue<Condition> conditions;
    public semaphore(int c , boolean isFear){
        this.c = c;
        this.isFear = isFear;
        lock = new ReentrantLock(isFear);
        conditions = new LinkedList<>();
    }

    public void release(){
        lock.lock();
        ++c;
        if (isFear) {
            Condition condition = conditions.peek();
            condition.signal();
        }
        else {
            for (Condition condition : conditions){
                condition.signal();
            }
        }
        lock.unlock();
    }

    public void acquire(){
        lock.lock();
        while (c == 0){
            try {
                Condition condition = lock.newCondition();
                conditions.add(condition);
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        --c;
        lock.unlock();
    }

    public static void main (String args[]){
        semaphore sm = new semaphore(3,true);
    }
}