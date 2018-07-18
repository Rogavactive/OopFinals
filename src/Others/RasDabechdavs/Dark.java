package Others.RasDabechdavs;

public class Dark {
    int x = 3;
    public static void main(String[] args){
        new Dark().go1();
    }

    void go1(){
        int x;
//        go2(++x);
        //compilation fails. method's x is not initialized
    }

    void go2(int y){
        int x = ++y;
        System.out.println(x);
    }

}
