package m4privatemethodsininterfaces;

import javax.print.attribute.standard.RequestingUserName;

public class Test {

    public static void main(String[] args) {
        Runnable r = new Runnable() {
            public void run() {
                System.out.println("Hello World");
            }
        };
    }
}
