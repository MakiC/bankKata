package com.mybank.service;

import com.mybank.domain.Transaction;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
/**
 * @author camara
 */
public class HistoryService {

    private final List<Transaction> TRANSACTIONS;

    public HistoryService(){
        this.TRANSACTIONS=new ArrayList<>();
    }

    public void add(Transaction transaction) {
        TRANSACTIONS.add(transaction);
    }


    public void printStatement(PrintStream out) {

        String HEADER = "OPERATION TYPE | Credit | Debit | Balance | Date |";

        out.println(HEADER);
        TRANSACTIONS.forEach(e->out.println(e));
    }
}
