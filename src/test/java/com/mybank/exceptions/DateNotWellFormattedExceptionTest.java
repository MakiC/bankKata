package com.mybank.exceptions;

import com.mybank.domain.Account;
import com.mybank.service.DateManagementService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class DateNotWellFormattedExceptionTest {

    @Test
    public void shouldThrowDateNotWellFormattedException(){
        Account account = new Account();
        Assertions.assertThrows(DateNotWellFormattedException.class, () -> {
                account.deposit(8000.0, DateManagementService.convertToDate("er/11/2020"));
            });
        }

}
