Given a client makes a deposit of 1000 on 10-10-2020
And a deposit of 2000 on 13-12-2020
And a withdrawal of 500 on 14-12-2020
When she prints her bank statement
Then she would see
Date       | Credit   | Debit    | Balance
14/12/2020 |          | 2200.00  | 2800.00
13/12/2020 | 2000.00  |          | 5000.00
10/12/2020 | 3000.00  |          | 3000.00