package com.mybank;

import com.mybank.domain.Account;
import com.mybank.exceptions.AmountExceededException;
import com.mybank.exceptions.DateNotWellFormattedException;
import com.mybank.exceptions.TransactionInvalidException;
import com.mybank.service.DateManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class LaunchBankApp {
    private static final Logger logger = LoggerFactory.getLogger(LaunchBankApp.class);

    public static void main(String[] args) {
        Account account = new Account();
        Date locDate;
        try {
            locDate = DateManagementService.convertToDate("10/11/2020");
            account.deposit(4500.0,locDate);
            locDate = DateManagementService.convertToDate("13/12/2020");
            account.deposit(2000.0, locDate);
            locDate = DateManagementService.convertToDate("14/12/2020");
            account.withdraw(-500.0, locDate);
        }
        catch(TransactionInvalidException transactionInvalidException) {
            logger.error("Transaction Not Valid:", transactionInvalidException);
        }
        catch (AmountExceededException amountExceededException){
            logger.error("Choose a lower amount, your balance is: ",amountExceededException);
        }
        catch (DateNotWellFormattedException dateNotWellFormattedException){
            logger.error("Date is not well formed",dateNotWellFormattedException);
        }
        finally {
            account.printStatement(System.out);
        }
    }
}
