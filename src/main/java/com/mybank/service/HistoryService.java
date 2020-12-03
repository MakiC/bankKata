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

        String HEADER = "OPERATION | Credit | Debit | Balance | Date |\n";
       TRANSACTIONS.sort((e1,e2)->-e1.getDATE().toInstant().compareTo(e2.getDATE().toInstant()));
        out.print(HEADER);
        TRANSACTIONS.forEach(e->out.print(e));
    }
}
