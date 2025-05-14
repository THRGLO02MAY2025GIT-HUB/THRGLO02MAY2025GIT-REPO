package labs.adapterwithobserver;
import java.util.ArrayList;
import java.util.List;

 class PaymentStatusSubject {
    private List<PaymentStatusObserver> observers = new ArrayList<>();
    private String status;

    // Add an observer
     public void addObserver(PaymentStatusObserver observer) {
         observers.add(observer);
     }
    // Remove an observer
     public void removeObserver(PaymentStatusObserver observer) {
         observers.remove(observer);
     }
    // Notify all observers
    public void notifyObservers() {
        for(PaymentStatusObserver observer : observers) {
            observer.update(status);
        }
    }
    // setStatus method placeholder .... work in progress
    public void setStatus(String status) {
        this.status = status;
        notifyObservers();
    }
}
