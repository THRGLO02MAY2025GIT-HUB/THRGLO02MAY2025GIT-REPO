package solid.dip;

public class DIPMain {
    public static void main(String[] args) {
        ReportStorage sqlStorage = new SQLStorage();     // or new S3Storage();
        DIPCompliantReportGenerator generator = new DIPCompliantReportGenerator(sqlStorage);
        generator.generateReport();  // Can easily switch to other storage
    }
}