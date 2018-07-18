package Others.RasDabechdavs;

enum Animals{
    DOG("woof"),CAT("meow"),FISH("burble");
    String sound;
    Animals (String s) { sound = s;}
}


public class TestEnum {

    static Animals a;
    public static void main(String[] args){
        System.out.println(a.DOG.sound + " " + a.FISH.sound);
    }


    public class Electronic implements Device {
        @Override
        public void doIt(){
            System.out.println("1");
        }
    }

    abstract class Phone1 extends Electronic {}

    abstract class Phone2 extends Electronic {
        public void doIt(int x){}
    }

    class Phone3 extends Electronic implements Device
    {public void doStuff(){}}

    interface Device{ public void doIt();}

    public abstract interface Frobnicate{public void twiddle(String s);}

    public abstract class Frob implements Frobnicate{}

    class Top {
        public Top(String s){
            System.out.println("B");
        }
    }

    public class Bottom2 extends Top{

        public Bottom2(String s) {
            super(s);
            System.out.println("D");
        }
    }

}
