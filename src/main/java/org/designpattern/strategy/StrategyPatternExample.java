package org.designpattern.strategy;


public class StrategyPatternExample {
    public static void main(String[] args) {
        PaymentContext paymentContext = new PaymentContext();

        paymentContext.setPaymentStrategy(new CreditCardPayment("1234"));
        paymentContext.pay(1000);
    }
}

class PaymentContext {

    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy){
        this.paymentStrategy = paymentStrategy;

    }

    public void pay(int amount){
        if(paymentStrategy == null){
            System.out.println("no payment");
        }
        else {
            paymentStrategy.pay(amount);
        }
    }
}

class GooglePayPayment implements PaymentStrategy {
    private String phoneNumber;

    public GooglePayPayment(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Google Pay linked to phone number: " + phoneNumber);
    }
}

class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using PayPal account: " + email);
    }
}

class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    public CreditCardPayment(String cardNumber){
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(int amount){
        System.out.println("Credit card ="+ amount);
    }
}

interface PaymentStrategy{
    void pay(int amount);
}