package solid.dip;

public class DIPCompliantReportGenerator {
    private final ReportStorage storage;
    // Constructor based DI
    public DIPCompliantReportGenerator(ReportStorage storage) {
        this.storage = storage;
    }
    public void generateReport() {
        storage.store("Daily Payment Report");
    }
}
