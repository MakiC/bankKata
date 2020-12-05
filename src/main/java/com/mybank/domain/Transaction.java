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
    private final OperationType OPERATION_TYPE;
    public static class TransactionBuilder{
        private OperationType OPERATION_TYPE;
        private Date DATE;
        private double amount;
        private double balance;

        /**
         * This Builder helps to better instanciate a Transaction
         */
        public TransactionBuilder(){}
        public TransactionBuilder withDate(Date date){
            this.DATE=date;
            return this;
        }
        public TransactionBuilder withOperationType(OperationType operationType){
            this.OPERATION_TYPE=operationType;
            return this;
        }
        public TransactionBuilder withAmount(double amount){
            this.amount=amount;
            return this;
        }
        public TransactionBuilder withBalance(double balance){
            this.balance=balance;
            return this;
        }
        public Transaction build(){
            return new Transaction(this);
        }
        
    }

    private Transaction(TransactionBuilder transactionBuilder){
        OPERATION_TYPE = transactionBuilder.OPERATION_TYPE;
        DATE=transactionBuilder.DATE;
        AMOUNT= transactionBuilder.amount;
        NEW_BALANCE=computeNewBalance(transactionBuilder.balance);
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

    public OperationType getOPERATION_TYPE() {
        return OPERATION_TYPE;

    }

    private Double computeNewBalance(Double balance) {
        int operationSign= OperationType.DEPOSIT.equals(OPERATION_TYPE)?1:-1;
        if(balance+operationSign*AMOUNT<0) throw new AmountExceededException("Choose a lower amount, your balance is "+ balance);
        else{balance+=operationSign*AMOUNT;}

        return balance;
    }

    @Override
    public String toString() {
      StringBuilder result=new StringBuilder();
      String creditColumn=" | ";
        String debitColumn=" | ";
      if(OperationType.WITHDRAWAL.equals(OPERATION_TYPE)) {creditColumn= " |       | ";}
      else{
          debitColumn= " |       | ";
      }

        result.append(getOPERATION_TYPE().toString())
                .append(creditColumn)
                .append(AMOUNT)
                .append(debitColumn)
                .append(NEW_BALANCE)
                .append(" | ")
                .append(DateManagementService.format(DATE))
                .append(" |")
                .append("\n");

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
