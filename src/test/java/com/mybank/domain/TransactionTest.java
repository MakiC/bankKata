package com.mybank.domain;

import com.mybank.service.DateManagementService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TransactionTest {
    @Test
    public void shouldComputeNewBalanceAfterWithdrawal()  {

        Transaction transaction=Account.getTransactionBuilder()
                .withOperationType(OperationType.WITHDRAWAL)
                .withDate(DateManagementService.convertToDate("03/12/2020"))
                .withAmount(5000.0)
                .withBalance(6000)
                .build();
        assertEquals("The new balance should be 1000 ",1000.0, transaction.getNEW_BALANCE(), 0);
    }

    @Test
    public void shouldComputeNewBalanceAfterDeposit()  {
        Transaction transaction=Account.getTransactionBuilder()
                .withOperationType(OperationType.DEPOSIT)
                .withDate(DateManagementService.convertToDate("05/12/2020"))
                .withAmount(5000.0)
                .withBalance(6000)
                .build();
         assertEquals("The new balance should be 11000 ",11000.0, transaction.getNEW_BALANCE(), 0);
    }
}
