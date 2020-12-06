package com.mybank.service;

import com.mybank.domain.Transaction;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author camara
 */
public class HistoryService {

    private final List<Transaction> transactionList;

    public HistoryService() {
        this.transactionList = new ArrayList<>();
    }

    public void add(Transaction transaction) {
        transactionList.add(transaction);
    }


    public void printStatement(PrintStream out) {

        String HEADER = "OPERATION | Credit | Debit | Balance | Date |\n";
        transactionList.sort((e1, e2) -> -e1.getDate().toInstant().compareTo(e2.getDate().toInstant()));
        out.print(HEADER);
        transactionList.forEach(out::print);
    }
}
