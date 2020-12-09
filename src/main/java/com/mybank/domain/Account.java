package com.mybank.domain;

import com.mybank.LaunchBankApp;
import com.mybank.exceptions.TransactionInvalidException;
import com.mybank.service.HistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.PrintStream;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author camara
 */
public class Account {
    private static final Logger logger = LoggerFactory.getLogger(LaunchBankApp.class);
    private final double INITIAL_BALANCE = 0;
    private Double balance = INITIAL_BALANCE;
    private final HistoryService history;
    private static final Transaction.TransactionBuilder transactionBuilder = new Transaction.TransactionBuilder();


    public Account() {
        history = new HistoryService();
    }

    /**
     *
     * @param credit : the amount to deposit
     * @param date : the given date
     * @throws TransactionInvalidException : in case of a negative amount,
     *              or a null date,
     */
    public void deposit(Double credit, Date date) {
        Transaction transaction = transactionBuilder
                .withOperationType(OperationType.DEPOSIT)
                .withDate(date)
                .withAmount(credit)
                .withBalance(balance).build();
        validateTransaction(transaction);
    }

    /**
     *
     * @param debit : the amount to withdraw
     * @param date : the given date
     * @throws TransactionInvalidException : in case of a negative amount,
     *              or an amount greater than the current balance,
     *              or a null Date
     */
    public void withdraw(Double debit, Date date) {
        Transaction transaction = transactionBuilder
                .withOperationType(OperationType.WITHDRAWAL)
                .withDate(date)
                .withAmount(debit)
                .withBalance(balance).build();
        validateTransaction(transaction);
    }

    private void validateTransaction(Transaction transaction) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Transaction>> constrainViolations = validator.validate(transaction);
        if (constrainViolations.size() > 0) {
            throw new TransactionInvalidException("Transaction Not Valid:" +
                    constrainViolations.stream().map(constraint ->
                            constraint.getPropertyPath() + " " + constraint.getMessage()).collect(Collectors.joining(",")));
        } else {
            balance = transaction.getNewBalance();
            history.add(transaction);
        }
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



