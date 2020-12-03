package katabank.domain;

import katabank.exception.AmountExcidedException;
import katabank.service.DateService;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author camara
 */
public class Account {
    private double balance;
    private List<Transaction> transactions;
    private HistoryService


    public Account(){
        balance=0;
        transactions=new ArrayList<Transaction>();
    }
    public void deposit(Double credit, String date) {
        balance+=credit;
        transactions.add(new Transaction(Operation.DEPOSIT,credit,date));
    }

    public void withdraw(Double debit, String date) {
      if(balance<debit) throw new AmountExcidedException("Choose a lower amount, your balance is "+ balance);
        balance-=debit;
    }

    public Double getBalance() {
        return this.balance;
    }

    public void printStatement(PrintStream out) {
                DateService loc=new DateService();
                loc.convertToDate();
           }


    }



}


