package solid.isp;

// Usage : Only What's Needed
public class NotificationService {
    private final EmailNotifier email;
    private final SMSNotifier sms;

    public NotificationService(EmailNotifier email, SMSNotifier sms) {
        this.email = email;
        this.sms = sms;
    }
    public void notifyCustomer(String emailAddress, String phone, String message) {
        email.sendEmail(emailAddress, message);
        sms.sendSMS(phone, message);
    }
}
