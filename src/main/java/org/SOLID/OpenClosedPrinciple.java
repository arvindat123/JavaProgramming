package org.SOLID;

/**
 * This principle states that “software entities (classes, modules, functions, etc.) should be open for extension,
 * but closed for modification” which means you should be able to extend a class behavior, without modifying it.
 */

public class OpenClosedPrinciple {

}

//Doesn't follow OCP
class NotificationsServiceClass {
    public void sendOTP(String medium) {
        if (medium.equals("email")) {
            //write email related logic
            //use JavaMailSenderAPI
        }
    }
}

//Follow OCP
interface NotificationsService {
    void sendOTP(String medium);

    void sendTransactionNotification(String medium);
}
class EmailNotification implements NotificationsService {
    public void sendOTP(String medium) {
// write Logic using JavaEmail api
    }

    public void sendTransactionNotification(String medium) {
    }
}

class MobileNotification implements NotificationsService {
    public void sendOTP(String medium) {
// write Logic using Twilio SMS API
    }

    public void sendTransactionNotification(String medium) {
    }
}

class WhatsAppNotification implements NotificationsService {
    public void sendOTP(String medium) {
// write Logic using whatsapp API
    }

    public void sendTransactionNotification(String medium) {
    }
}