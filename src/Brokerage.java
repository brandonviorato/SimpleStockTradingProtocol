/**
 A brokerage consisting of multiple bank accounts
 and 3 tradeable stocks.
 */
public class Brokerage
{
    private BrokerageAccount[] accounts;
    private Stock[] stocks;

    /**
     Constructs a brokerage account with a given number of accounts
     and 3 stocks.
     @param accountsSize the number of accounts
     @param stocksSize the number of accounts
     */
    public Brokerage(int accountsSize, int stocksSize)
    {
        accounts = new BrokerageAccount[accountsSize];
        stocks = new Stock[stocksSize];
        for (int i = 0; i < accounts.length; i++)
        {
            accounts[i] = new BrokerageAccount();
        }
        Stock aapl = new Stock("AAPL");
        Stock cost = new Stock("COST");
        Stock msft = new Stock("MSFT");

        stocks[0] = aapl;
        stocks[1] = cost;
        stocks[2] = msft;
    }

    public void buy(int accountNumber, String symbol, int quantity) {
        BrokerageAccount account = accounts[accountNumber];
        try {
            Stock stock = findStock(symbol);
            account.buy(stock, quantity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void sell(int accountNumber, String symbol, int quantity) {
        BrokerageAccount account = accounts[accountNumber];
        try {
            Stock stock = findStock(symbol);
            account.sell(stock, quantity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // helper method to match string symbol to stock class
    private Stock findStock(String symbol) throws Exception {
        for (Stock stock : stocks) {
            if (stock.getSymbol().equals(symbol)) {
                return stock;
            }
        }
        throw new Exception("Error: Symbol Not Found");
    }

    /**
     Deposits money into a brokerage account.
     @param accountNumber the account number
     @param amount the amount to deposit
     */
    public void deposit(int accountNumber, double amount)
    {
        BrokerageAccount account = accounts[accountNumber];
        account.deposit(amount);
    }

    /**
     Withdraws money from a brokerage account.
     @param accountNumber the account number
     @param amount the amount to withdraw
     */
    public void withdraw(int accountNumber, double amount)
    {
        BrokerageAccount account = accounts[accountNumber];
        account.withdraw(amount);
    }

    /**
     Gets the balance of a brokerage account.
     @param accountNumber the account number
     @return the account balance
     */
    public double getBalance(int accountNumber)
    {
        BrokerageAccount account = accounts[accountNumber];
        return account.getBalance();
    }

    /**
     Gets the current price of a stock.
     @param symbol the stock symbol
     @return the stock price
     */
    public double getPrice(String symbol) {
        try {
            Stock stock = findStock(symbol);
            return stock.getPrice();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public String getPortfolio(int accountNumber)
    {
        BrokerageAccount account = accounts[accountNumber];
        return account.view();
    }
}