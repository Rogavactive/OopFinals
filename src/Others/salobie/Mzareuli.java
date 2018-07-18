package Others.salobie;

public class Mzareuli extends Thread {
    int capacity;
    Salobie salobie;

    Mzareuli(int cap, Salobie s) {
        capacity = cap;
        salobie = s;
    }

    // 1. თუკი გაქვს ადგილი და შეკვეთა არის მაშინ
    // აიღე ის შემომავალი სინიდან
    // 2. გააკეთე ლობიო
    public void run() {
        while (true) {


            try {
                if(capacity == 0)
                    wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            salobie.in.remove(0);
            synchronized(this) {
                capacity--;
            }
            Lobio newLobio = new Lobio(salobie);
            newLobio.cookMe(this);

        }
    }

}
