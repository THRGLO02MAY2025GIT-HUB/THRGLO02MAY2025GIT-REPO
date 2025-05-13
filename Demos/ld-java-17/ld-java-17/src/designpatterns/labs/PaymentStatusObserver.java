package designpatterns.labs;

//Observer interface for receiving updates
public interface PaymentStatusObserver {
    void update(String status);
}
