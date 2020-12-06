package com.mybank;

import com.mybank.domain.Account;
import com.mybank.service.DateManagementService;

public class LaunchBankApp {


    public static void main(String[] args) {
        Account account = new Account();
        account.deposit(4500.0, DateManagementService.convertToDate("10/11/2020"));
        account.deposit(2000.0, DateManagementService.convertToDate("13/12/2020"));
        account.withdraw(500.0, DateManagementService.convertToDate("14/12/2020"));
        account.printStatement(System.out);
    }


}
