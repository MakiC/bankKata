package com.mybank.exceptions;

import com.mybank.domain.Account;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TransactionInvalidExceptionTest {


    @Test
    public void shouldThrowTransactionInvalidExceptionSinceAmountIsNeg() throws ParseException {
        Account account = new Account();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        account.deposit(5000.0, dateFormat.parse("03/12/2020"));
        account.deposit(1000.0, dateFormat.parse("07/12/2020"));
        Assertions.assertThrows(TransactionInvalidException.class, () -> {
            account.withdraw(-8000.0, dateFormat.parse("05/12/2020"));
        });
    }

    @Test
    public void shouldThrowTransactionInvalidExceptionSinceDateIsNull() throws ParseException {
        Account account = new Account();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        account.deposit(5000.0, dateFormat.parse("03/12/2020"));
        account.deposit(1000.0, dateFormat.parse("07/12/2020"));
        Assertions.assertThrows(TransactionInvalidException.class, () -> {
            account.withdraw(3000.0, null);
        });
    }
}
