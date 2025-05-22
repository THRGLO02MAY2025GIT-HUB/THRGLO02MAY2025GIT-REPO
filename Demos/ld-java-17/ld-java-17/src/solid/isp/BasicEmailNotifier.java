package solid.isp;

class BasicEmailNotifier implements EmailNotifier {
    public void sendEmail(String address, String message) {
        System.out.println("[Email] Sent to: " + address + " | Message: " + message);
    }
}

class TwilioSMSNotifier implements SMSNotifier {
    public void sendSMS(String phoneNumber, String message) {
        System.out.println("[SMS] To: " + phoneNumber + " | Message: " + message);
    }
}

class OpsSlackNotifier implements SlackNotifier {
    public void sendSlack(String channel, String message) {
        System.out.println("[Slack] #" + channel + " | " + message);
    }
}