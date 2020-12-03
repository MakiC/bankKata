Feature: Account Management

  Scenario: make a deposit and a withdraw and print account statement
    Given I deposit money 3000.00 on the date "02/12/2020"
      And I withdraw money 1500.00 on the date "17/12/2020"
    When I print account statement
    Then I should see
     """OPERATION | Credit | Debit | Balance | Date |
     OPERATION | Credit | Debit | Balance | Date |
     WITHDRAW |       | 1500.0 | 1500.0 | 17/12/2020 |
     DEPOSIT | 3000.0 |       | 3000.0 | 02/12/2020 |

     """
