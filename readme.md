
Simple Stock Trading Protocol (SSTP)\
This is a simple program that demonstrates the possible implementation 
of a client-server protocol for the purpose of trading stocks. Currently,
you can buy, sell, and view the price of 3 stocks: Apple, Costco, and Microsoft


| Client Request | Server Response                                               | Description                                 |
|----------------|---------------------------------------------------------------|---------------------------------------------|
| BALANCE n      | n and the balance                                             | Get the balance of account n                |
| DEPOSIT n a    | n and the new balance                                         | Deposit amount a into account n             |
| WITHDRAW n a   | n and the new balance                                         | Withdraw amount a from account n            |
| PRICE s        | s and the current price                                       | Get the current price of stock s            |
| BUY n s q      | n and the remaining balance                                   | Purchase quantity q of stock s on account a |
| SELL n s q     | n and the new balance                                         | Sell quantity q of stock s on account n     |
| VIEW n         | The balance, stocks owned, quantities, and value of account n | View account n's portfolio                  |
| QUIT           | Quit the connection                                           |                                             |



Source: Brandon Viorato\
Based on Cay Horstmann & Big Java's Simple Bank Access Protocol