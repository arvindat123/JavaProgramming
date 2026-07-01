package org.Test;

import java.util.concurrent.atomic.AtomicLong;

public class BankAccount extends Thread{
    volatile long balance = 0;

    public void deposit(long depositAmount){
        balance = balance + depositAmount;
    }
    public void withdraw(long withdrawAmount){
        if(balance >= withdrawAmount) {
            balance = balance - withdrawAmount;
        } else {
            System.out.println("There is no sufficient balance to withdraw");
        }
    }
    public long currentBalance(){
        return balance;
    }

    public static void main(String[] args) {
        BankAccount bankAccount1 = new BankAccount();
        Thread T1 = new Thread(bankAccount1);
        Thread T2 = new Thread(bankAccount1);
        T1.start();
        T2.start();
    }
}
