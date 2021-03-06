package com.mybank.domain;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class AccountTest {

    @Test
    public void shouldReturnBalanceEqualsToMyDeposit() throws ParseException {
        Account account = new Account();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        account.deposit(5000.0, dateFormat.parse("03/12/2020"));
        assertEquals("The balance should be 5000 ", 5000.0, account.getBalance(), 0);
    }

    @Test
    public void shouldThrowBalanceEqualsToMyDepositMinusMyWithdrawal() throws ParseException {
        Account account = new Account();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        account.deposit(5000.0, dateFormat.parse("03/12/2020"));
        account.withdraw(3000.0, dateFormat.parse("05/12/2020"));
        assertEquals("The balance should be 5000 - 3000 = 2000", 2000.0, account.getBalance(), 0);
    }

    @Test
    public void shouldThrowBalanceEqualsToMyDeposit() throws ParseException {
        Account account = new Account();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        account.deposit(5000.0, dateFormat.parse("03/12/2020"));
        assertEquals("The balance should be 5000 ", 5000.0, account.getBalance(), 0);
    }

    @Test
    public void shouldReturnBalanceEqualsToMyDepositMinusMyWithdrawal() throws ParseException {
        Account account = new Account();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        account.deposit(5000.0, dateFormat.parse("03/12/2020"));
        account.withdraw(3000.0, dateFormat.parse("05/12/2020"));
        assertEquals("The balance should be 5000 - 3000 = 2000", 2000.0, account.getBalance(), 0);
    }

    @Test
    public void shouldReturnPrintedStatementEqualsToExpectedStatement() throws ParseException, IOException {
        Account account = new Account();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        account.deposit(1000.0, dateFormat.parse("01/11/2020"));
        account.deposit(5000.0, dateFormat.parse("03/12/2020"));
        account.withdraw(3000.0, dateFormat.parse("05/12/2020"));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream console = System.out;
        System.setOut(new PrintStream(outContent));
        account.printStatement(System.out);
        System.setOut(console);
        String expectedOutput = new Scanner(new File("src/test/resources/com/mybank/account/expectedStatement.txt")).useDelimiter("\\Z")
                .next();
        expectedOutput = expectedOutput.replaceAll("\r", "");

        assertEquals("The printed statement should be the same as expected statement", expectedOutput, outContent.toString());

    }
}

