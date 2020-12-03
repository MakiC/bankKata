package com.mybank.accceptance;

import com.mybank.domain.Account;
import com.mybank.service.DateManagementService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @author camar
 */
public class StepDefinitions {
    Account account=new Account();
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    @Given("I deposit money {double} on the date {string}")
    public void iDepositMoneyOnTheDate(Double debit, String dateInString) {
        Date date= DateManagementService.convertToDate(dateInString);
         account.deposit(debit,date);
    }

    @Given("I withdraw money {double} on the date {string}")
    public void iWithdrawMoneyOnTheDate(Double double1, String string) {
        Date date=DateManagementService.convertToDate(string);
         account.withdraw(double1,date);
    }
    @When("I print account statement")
    public void iPrintAccountStatement() {
        System.setOut(new PrintStream(outContent));
        account.printStatement(System.out);
    }
    @Then("I should see")
    public void iShouldSee(String expectedOutput) {
        assertEquals(expectedOutput, outContent.toString());
    }
}

/**
 *  Account account=new Account();
 *     ByteArrayOutputStream outContent = new ByteArrayOutputStream();
 *     @Given("I deposit money {credit} on the date {dateInString}")
 *     public void iDepositMoneyOnTheDate(Double credit, String dateInString ) {
 *         Date date= DateManagementService.convertToDate(dateInString);
 *        account.deposit(credit,date);
 *     }
 *
 *
 *     @Given("I withdraw money {debit} on the date{dateInString}")
 *     public void iWithdrawMoneyOnTheDate(Double debit, String dateInString) {
 *         Date date=DateManagementService.convertToDate(dateInString);
 *        account.deposit(debit,date);
 *     }
 *     @When("I print account statement")
 *     public void iPrintAccountStatement() {
 *         System.setOut(new PrintStream(outContent));
 *         account.printStatement(System.out);
 *     }
 *     @Then("I should see")
 *     public void iShouldSee(String expectedOutput) {
 *         assertEquals(expectedOutput, outContent.toString());
 *     }
 */