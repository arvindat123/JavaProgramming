package org.SOLID;

/**
 * Single Responsibility Principle : Each class should be responsible for a single part or functionality of the system.
 * Open-Closed Principle : Software components should be open for extension, but not for modification.
 * Liskov Substitution Principle: Objects of a superclass should be replaceable with objects of its subclasses without breaking the system.
 * Interface Segregation Principle: No client should be forced to depend on methods that it does not use.
 * Dependency Inversion Principle High-level modules should not depend on low-level modules, both should depend on abstractions.
 */
//a class should have only one reason to change
//Below class doesn't follow SRP
public class SingleResponsibilityPrinciple {
    public long deposit(long amount, String accountNumber) {
        //deposit amount
        return 0;
    }

    public long withDraw(long amount, String accountNumber) {
        //withdraw amount
        return 0;
    }

    public void printPassBook() {
        //printPassbook
    }

    public void getLoanInterestInfo(String loanType) {
        if (loanType.equals("homeLoan")) {
            //do some job
        }
        if (loanType.equals("personalLoan")) {
            //do some job
        }
        if (loanType.equals("car")) {
            //do some job
        }
    }

    public void sendOTP(String medium) {
        if (medium.equals("email")) {
            //write email related logic
            //use JavaMailSenderAPI
        }
    }
}
//below class follows SRP
class PrinterService {
    public void printPassBook() {
        //printPassbook
    }
}

class LoanService {
    public void getLoanInterestInfo(String loanType) {
        if (loanType.equals("homeLoan")) {
            //do some job
        }
        if (loanType.equals("personalLoan")) {
            //do some job
        }
        if (loanType.equals("car")) {
            //do some job
        }
    }
}
class NotificationService {
    public void sendOTP(String medium) {
        if (medium.equals("email")) {
            //write email related logic
            //use JavaMailSenderAPI
        }
    }
}