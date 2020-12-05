package com.mybank.domain;

import com.mybank.service.HistoryService;

import java.io.PrintStream;
import java.util.Date;

/**
 * @author camara
 */
public class Account {
    private final double INITIAL_BALANCE = 0;
    private Double balance = INITIAL_BALANCE;
    private final HistoryService history;
    private static final Transaction.TransactionBuilder transactionBuilder=new Transaction.TransactionBuilder();;

    public Account() {
        history=new HistoryService();
    }

    public void deposit(Double credit, Date date) {
        Transaction transaction=transactionBuilder
                .withOperationType(OperationType.DEPOSIT)
                .withDate(date)
                .withAmount(credit)
                .withBalance(balance).build();
        balance=transaction.getNEW_BALANCE();
        history.add(transaction);
    }

    public void withdraw(Double debit, Date date) {
        Transaction transaction= transactionBuilder
                .withOperationType(OperationType.WITHDRAWAL)
                .withDate(date)
                .withAmount(debit)
                .withBalance(balance).build();

        balance=transaction.getNEW_BALANCE();
        history.add(transaction);
    }
    public Double getBalance() {
        return balance;
    }

    public static Transaction.TransactionBuilder getTransactionBuilder() {
        return transactionBuilder;
    }

    public void printStatement(PrintStream out) {
               history.printStatement(out);
           }
    }



