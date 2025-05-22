package solid.dip;

class SQLStorage implements ReportStorage {
    public void store(String reportContent) {
        System.out.println("[SQL] Stored report: " + reportContent);
    }
}

class S3Storage implements ReportStorage {
    public void store(String reportContent) {
        System.out.println("[S3] Uploaded report: " + reportContent);
    }
}