package services;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class SmsSender {
    // Find your Account Sid and Token at twilio.com/console
    public static final String ACCOUNT_SID = "AC48820a895756ae4d5b4ae5d0a965c0ed";
    public static final String AUTH_TOKEN = "3913891b0dd7a03d420eaf77d46bb03d";

    public static void sendSms(String numeroDestinataire, String contenuMessage) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber("+216"+numeroDestinataire),
                        new com.twilio.type.PhoneNumber("+17817421532"),
                        contenuMessage)
                .create();

        System.out.println(message.getSid());
    }
}