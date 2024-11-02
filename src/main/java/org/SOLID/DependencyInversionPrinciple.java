package org.SOLID;

/**
 * The principle states that we must use abstraction (abstract classes and interfaces)
 * instead of concrete implementations.
 * High-level modules should not depend on the low-level module but both should depend on the abstraction
 */
public class DependencyInversionPrinciple {
}

//if you observe this is wrong design of coding , now ShoppingMall class tightly coupled with DebitCard, creditcard
class DebitCard {
    public void doTransaction(int amount) {
        System.out.println("tx done with DebitCard");
    }
}

class CreditCard {
    public void doTransaction(int amount) {
        System.out.println("tx done with CreditCard");
    }
}

class ShoppingMall {
    private DebitCard debitCard;

    public ShoppingMall(DebitCard debitCard) {
        this.debitCard = debitCard;
    }

    public static void main(String[] args) {
        DebitCard debitCard = new DebitCard();
        ShoppingMall shoppingMall = new ShoppingMall(debitCard);
        shoppingMall.doPayment("some order", 5000);
    }

    public void doPayment(Object order, int amount) {
        debitCard.doTransaction(amount);
    }
}
/**
 * now there is some error in your debit card and
 * user want to go with Credit card then this won’t be possible because ShoppingMall is tightly couple with Debit Card
 */

//To simplify this designing principle further i am creating a interface called Bankcards like bellow
interface BankCard {
    public void doTransaction(int amount);
}

class CreditCard1 implements BankCard {
    public void doTransaction(int amount) {
        System.out.println("tx done with CreditCard");
    }
}
class DebitCard1 implements BankCard {
    public void doTransaction(int amount) {
        System.out.println("tx done with DebitCard");
    }
}

class ShoppingMall1 {
    private BankCard bankCard;

    public ShoppingMall1(BankCard bankCard) {
        this.bankCard = bankCard;
    }

    public static void main(String[] args) {
        BankCard bankCard = new CreditCard1();
        ShoppingMall1 shoppingMall1 = new ShoppingMall1(bankCard);
        shoppingMall1.doPayment("do some order", 10000);
    }

    public void doPayment(Object order, int amount) {
        bankCard.doTransaction(amount);
    }
}