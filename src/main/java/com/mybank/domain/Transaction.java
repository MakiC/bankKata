package com.mybank.domain;

import com.mybank.exception.AmountExceededException;
import com.mybank.service.DateManagementService;

import java.util.Date;
import java.util.Objects;

/**
 * @author camara
 */
public class Transaction {

    private final Date DATE;
    private final double NEW_BALANCE ;
    private final double AMOUNT;
    private final Operation OPERATION_TYPE;

    public Transaction(Operation operation, Date date,Double amount, double balance) {
        OPERATION_TYPE =operation;
        DATE=date;
        AMOUNT= amount;
        NEW_BALANCE=computeNewBalance(balance);
    }

    public Date getDATE() {
        return DATE;
    }

    public double getNEW_BALANCE() {
        return NEW_BALANCE;
    }

    public double getAMOUNT() {
        return AMOUNT;
    }

    public Operation getOPERATION_TYPE() {
        return OPERATION_TYPE;

    }

    private Double computeNewBalance(Double balance) {
        int operationSign= Operation.DEPOSIT.equals(OPERATION_TYPE)?1:-1;
        if(balance+operationSign*AMOUNT<0) throw new AmountExceededException("Choose a lower amount, your balance is "+ balance);
        else{balance+=operationSign*AMOUNT;}

        return balance;
    }

    @Override
    public String toString() {
      StringBuilder result=new StringBuilder();
      String creditOrDebitColumn="";
      if(Operation.WITHDRAW.equals(OPERATION_TYPE)) creditOrDebitColumn= "\t\\t";

        result.append(getOPERATION_TYPE().toString())
                .append(" | ")
                .append(AMOUNT)
                .append(" | ")
                .append(creditOrDebitColumn)
                .append(NEW_BALANCE)
                .append(DateManagementService.format(DATE))
                .append(" |");

        return result.toString();

    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        if(DATE==null||OPERATION_TYPE==null) return false;
        return Double.compare(that.NEW_BALANCE, NEW_BALANCE) == 0 &&
                Double.compare(that.AMOUNT, AMOUNT) == 0 &&
                DATE.equals(that.DATE) && OPERATION_TYPE.equals(that.OPERATION_TYPE);
    }

    @Override
    public int hashCode() {
        return Objects.hash(DATE, NEW_BALANCE, AMOUNT);
    }
}
