package solid.isp;

interface EmailNotifier {
    void sendEmail(String address, String message);
}

interface SMSNotifier {
    void sendSMS(String phoneNumber, String message);
}

interface SlackNotifier {
    void sendSlack(String channel, String message);
}