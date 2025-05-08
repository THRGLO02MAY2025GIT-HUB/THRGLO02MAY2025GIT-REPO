package m7compactstring;

public class CompactStringPerformanceDemo {
    public static final int TRANSACTION_COUNT = 1_000_000;
    public static void main(String[] args) {
        //Before Java 9 uses 2 bytes per character
        String transaction = new String("TXN123");
        //After Java 9 uses 1 byte per character when possible
        String transaction1 = new String("TXN123");

        System.out.println("JDK Version : " + System.getProperty("java.version"));
        for(int i =0; i< 5; i++){
            testCompactStringPerformance();
        }
    }
    public static void testCompactStringPerformance() {
        //Some clean up
        System.gc();
        System.gc();
        long beforeMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        StringBuilder builder = new StringBuilder(TRANSACTION_COUNT * 30);
        for(int i = 0; i < TRANSACTION_COUNT; i++) {
            String txnId = new String("TXN" + i);
            String amount = " USD" + Math.random()*100000;
            String record = txnId + amount;
            builder.append(record);
        }
         long afterMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        System.out.println(" Total Memory Used : " + ((afterMemory - beforeMemory) / (1024 * 1024)) + "MB");
    }
}
