package org.SOLID;

/**
 * This principle is the first principle that applies to Interfaces instead of classes in SOLID
 * , it is similar to the single responsibility principle.
 * It states that 'do not force any client to implement an interface which is irrelevant to them'.
 */
public class InterfaceSegregationPrinciple {
}

interface UPIPayments {

    public void payMoney();

    public void getScratchCard();

    public void getCashBackAsCreditBalance();
}
/**
 * Google Pay support these features so he can directly implement this UPIPayments
 * but Paytm doesn’t support getCashBackAsCreditBalance() feature
 * so here we shouldn’t force client paytm to override this method by implementating UPIPayments .
 */
interface CashbackManager {
    public void getCashBackAsCreditBalance();
}