package multithreading;

public class SingleThreadedEx {
    public static void main(String[] args) {
        System.out.println("Single Threaded App! ONE process with ONE Thread");
        Thread currentThread = Thread.currentThread();
        currentThread.setName("Thread-main");
        System.out.println(currentThread.getName());

        Compute compute = new Compute();
        compute.even();
        compute.odd();
        System.out.println("End of the program!");
    }
}
class Compute {
    public void even(){
        for (int i = 2; i <= 50; i+=2) {
            System.out.println("EVEN " + i + " " + Thread.currentThread().getName()) ;
        }
    }
    public void odd(){
        for (int i = 1; i <= 50; i+=2) {
            System.out.println("ODD " + i + " " + Thread.currentThread().getName()) ;
        }
    }
}
