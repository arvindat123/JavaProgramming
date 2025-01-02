package org.designpattern.strategy;

interface PaymentStrategy {
    void pay(int amount);
}

//Implement specific payment method
//Credit card payment
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    CreditCardPayment(String cardNumber){
        this.cardNumber = cardNumber;
    }
    @Override
    public void pay(int amount){
        System.out.println("Paid "+ amount + " using Credit Card "+ cardNumber);
    }
}

//Paypal payment
class PayPalPaymentMethod implements PaymentStrategy {
    private String email;

    PayPalPaymentMethod(String email){
        this.email = email;
    }
    @Override
    public void pay(int amount){
        System.out.println("Paid "+ amount + " using pay pal "+ email);
    }
}

//Using Google Pay
class GooglePay implements PaymentStrategy {
    private String phoneNumber;
    GooglePay(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    @Override
    public void pay(int amount) {
        System.out.println("Paid "+ amount + " using google pay" + phoneNumber);
    }
}


public class StrategyPatternExample {
    public static void main(String[] args) {
        PaymentStrategy strategy = new CreditCardPayment("1234545");
        strategy.pay(50);
    }
}
