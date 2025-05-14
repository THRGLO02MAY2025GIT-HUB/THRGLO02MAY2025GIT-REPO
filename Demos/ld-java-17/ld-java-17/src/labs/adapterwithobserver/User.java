package labs.adapterwithobserver;
public class User implements PaymentStatusObserver {
    private String name;

    public User(String name) {
        this.name = name;
    }
    @Override
    public void update(String status) {
        System.out.println("User " + name + " notified on mobile. " + status);
        System.out.println("User " + name + " notified on website. " + status);
    }
}

