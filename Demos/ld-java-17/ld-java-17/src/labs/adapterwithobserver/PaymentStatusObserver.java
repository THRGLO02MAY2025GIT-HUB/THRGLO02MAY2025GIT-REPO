package labs.adapterwithobserver;

//Observer interface for receiving updates
 interface PaymentStatusObserver {
    void update(String status);
}
