
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class QueueManager {
    private ReentrantLock lock = new ReentrantLock();
    private Worker worker;
    ArrayList<HttpSession> queue = new ArrayList<>();

    public QueueManager(int interval){
        worker = new Worker(interval);
        worker.start();
    }

    public void Dispose(){
        worker.interrupt();
    }

    public int getQueueNumber(HttpSession session) {
        lock.lock();
        int result = -1;
        int remainingUsers = worker.getUsersNum();
        if(!queue.contains(session)){
            queue.add(session);
        }
        int numInQueue = queue.indexOf(session)+1;
        result = numInQueue-remainingUsers;
        if(result<=0){
            worker.reduceUsersNum();
            result = 0;
            queue.remove(session);
        }
        lock.unlock();
        return result;
    }

    private class Worker extends Thread{
        private int usersNum = 0;
        private Random rand = new Random();
        private int interval;

        public Worker(int refreshInterval){
            interval = refreshInterval;
        }

        @Override
        public void run(){
            while(true){
                usersNum = rand.nextInt(3);//insteadof Utils.getAvaliableUsersCount();
                try {
                    sleep(interval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }

        public void reduceUsersNum(){
            usersNum--;
        }

        public int getUsersNum(){
           return usersNum;
        }
    }
}
