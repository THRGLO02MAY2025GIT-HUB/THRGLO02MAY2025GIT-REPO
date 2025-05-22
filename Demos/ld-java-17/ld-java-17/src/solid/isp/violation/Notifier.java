package solid.isp.violation;

interface Notifier {
    void sendEmail(String address, String message);
    void sendSMS(String phoneNumber, String message);
    void sendSlack(String channel, String message);
}

// A notifier that only supports email
class EmailOnlyNotifier implements Notifier {
    public void sendEmail(String address, String message) {
        System.out.println("[Email] To: " + address + ", Msg: " + message);
    }

    public void sendSMS(String phoneNumber, String message) {
        throw new UnsupportedOperationException("SMS not supported");
    }

    public void sendSlack(String channel, String message) {
        throw new UnsupportedOperationException("Slack not supported");
    }
}