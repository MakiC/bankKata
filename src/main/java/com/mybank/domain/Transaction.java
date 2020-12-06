package com.mybank.domain;

import com.mybank.exceptions.AmountExceededException;
import com.mybank.service.DateManagementService;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

/**
 * @author camara
 */
public class Transaction {
    @NotNull
    private final Date date;
    private final double newBalance;
    @Min(0)
    private final double amount;
    private final OperationType operationType;

    /**
     * This Builder helps to better instanciate a Transaction
     */
    public static class TransactionBuilder {
        private OperationType operationType;
        private Date date;
        private double amount;
        private double balance;

        public TransactionBuilder withDate(Date date) {
            this.date = date;
            return this;
        }

        public TransactionBuilder withOperationType(OperationType operationType) {
            this.operationType = operationType;
            return this;
        }

        public TransactionBuilder withAmount(double amount) {
            this.amount = amount;
            return this;
        }

        public TransactionBuilder withBalance(double balance) {
            this.balance = balance;
            return this;
        }

        public Transaction build() {
            return new Transaction(this);
        }

    }

    private Transaction(TransactionBuilder transactionBuilder) {
        operationType = transactionBuilder.operationType;
        date = transactionBuilder.date;
        amount = transactionBuilder.amount;
        newBalance = computeNewBalance(transactionBuilder.balance);
    }

    public Date getDate() {
        return date;
    }

    public double getNewBalance() {
        return newBalance;
    }

    public double getAmount() {
        return amount;
    }

    public OperationType getOperationType() {
        return operationType;

    }

    private Double computeNewBalance(Double balance) {
        int operationSign = OperationType.DEPOSIT.equals(operationType) ? 1 : -1;
        if (balance + operationSign * amount < 0) {
            throw new AmountExceededException("Choose a lower amount, your balance is " + balance);
        } else {
            balance += operationSign * amount;
        }

        return balance;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String creditColumn = " | ";
        String debitColumn = " | ";
        if (OperationType.WITHDRAWAL.equals(operationType)) {
            creditColumn = " |       | ";
        } else {
            debitColumn = " |       | ";
        }

        result.append(getOperationType().toString())
                .append(creditColumn)
                .append(amount)
                .append(debitColumn)
                .append(newBalance)
                .append(" | ")
                .append(DateManagementService.format(date))
                .append(" |")
                .append("\n");

        return result.toString();

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Transaction that = (Transaction) o;
        if (date == null || operationType == null) {
            return false;
        }
        return Double.compare(that.newBalance, newBalance) == 0 &&
                Double.compare(that.amount, amount) == 0 &&
                date.equals(that.date) && operationType.equals(that.operationType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, newBalance, amount);
    }
}
