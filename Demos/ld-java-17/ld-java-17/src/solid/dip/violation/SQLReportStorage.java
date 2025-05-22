package solid.dip.violation;

class SQLReportStorage {
    public void store(String reportContent) {
        System.out.println("[SQL] Stored report: " + reportContent);
    }
}
// Violation :
// Tightly Coupled to Low-Level classes
// Wrong : ReportGenerator is directly dependent on SQL storage
// Swapping backends to (NoSQL, S3) cannot happen without changing the generateReport() method.
// Violates both OCP (Open/Closed Principle) and DIP
class HardCodedReportGenerator {
    public void generateReport() {
        SQLReportStorage storage = new SQLReportStorage();  // ❌ Concrete dependency
        storage.store("Daily Payment Report");
    }
}