package Salobie;

public class Lobio {
    int cookTime = 5000;
    Salobie r;
    Lobio(Salobie r) {
        r = r;
    }

    String cookMe (final Mzareuli mzareuli) {
        // 1. შექმენით ახალი ნაკადი.
        // 2. დაიძინეთ cookTime დროით.
        // 3. გააგებინეთ მზარეულს რომ ლობიო გაკეთდა.
        // 4. დადეთ ლობიო გამომავალ სინზე.
        (new Thread (){
            public void run () {
                try {
                    Thread.sleep(cookTime);
                }
                catch (InterruptedException e) {}

                synchronized (r.out){
                    r.out.add(this);
                }
                // დაწერეთ თქვენი კოდი აქ
            }
        }).start();
        return null;
    }

}


