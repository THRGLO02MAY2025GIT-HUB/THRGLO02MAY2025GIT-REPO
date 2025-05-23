package asynchronous;

import java.util.concurrent.CompletableFuture;

// Use Case :
// Model asynchronous approval like a fraud check that requires human intervention
// Main Thread : Setup the Future and its dependent action, and moves on.
// When the Analyst thread completes the future, the continuation will happen.
// Integrating manual or event-driven completion into async workflows using CompletableFuture
public class AsynchronousProgrammingWithCompletableFutureForFraudApproval {
    public static void main(String[] args) {
        System.out.println("Main thread Setup the Future");
        CompletableFuture<Void> manualApproval = new CompletableFuture<>();
        String transactionId = "TXN-PAY-0001";
        // Create a new Thread named "Fraud-Review-Thread"
        System.out.println("Analyst is performing his Job asychronously !");
        new Thread(() -> {
            try {
                Thread.sleep(5000); //Simulating analyst delay
                System.out.println("[FRAUD] Analyst approved trasaction: " + transactionId);
                manualApproval.complete(null);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"Fraud-Review-Thread").start();
        System.out.println("The MAIN THEREAD IS MOVING ON!!");
        manualApproval.thenRun(()-> System.out.println("[PAYMENT] Transaction " + transactionId + " approved and dispatched for settlement"));
        System.out.println("The MAIN THEREAD IS DOING TASK !!");
    }
}
