import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 A brokerage account has a balance that can be changed by
 stock buys and sells as well as deposits and withdrawals.
 Also contains a portfolio of current stock holdings
 */
public class BrokerageAccount {
    private double balance;
    private HashMap<Stock, Integer> portfolio;
    private Lock balanceChangeLock;

    /**
     Constructs a brokerage account with a zero balance
     and empty portfolio.
     */
    public BrokerageAccount() {
        balance = 0;
        portfolio = new HashMap<>();
        balanceChangeLock = new ReentrantLock();
    }

    /**
     Adds shares to portfolio and removes cost from balance.
     @param stock the stock being added
     @param quantity the amount of shares
     */
    public void buy(Stock stock, int quantity) {
        balanceChangeLock.lock();
        try {
            // check if stock is already in portfolio
            if (portfolio.containsKey(stock)) {
                // increment quantity owned
                portfolio.put(stock, portfolio.get(stock) + quantity);
            }
            else {
                // add new stock to portfolio
                portfolio.put(stock, quantity);
            }
            // subtract price of stocks from balance
            balance = balance - (stock.viewPrice() * quantity);
        }
        finally {
            balanceChangeLock.unlock();
        }
    }

    /**
     Removes shares from portfolio and adds profits to balance.
     @param stock the stock being removed
     @param quantity the amount of shares
     */
    public void sell(Stock stock, int quantity) {
        balanceChangeLock.lock();
        try {
            // check if stock is in portfolio
            if (portfolio.containsKey(stock)) {
                // subtract shares from portfolio
                portfolio.put(stock, portfolio.get(stock) - quantity);
            }
            else {
                throw new Exception("Error: Stock not found");
            }
            // update balance
            balance = balance + (stock.viewPrice() * quantity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            balanceChangeLock.unlock();
        }
    }

    /**
     Deposits money into the brokerage account.
     @param amount the amount to deposit
     */
    public void deposit(double amount) {
        balanceChangeLock.lock();
        try
        {
            double newBalance = balance + amount;
            balance = newBalance;
        }
        finally
        {
            balanceChangeLock.unlock();
        }
    }

    /**
     Withdraws money from the brokerage account.
     @param amount the amount to withdraw
     */
    public void withdraw(double amount) {
        balanceChangeLock.lock();
        try {
            double newBalance = balance - amount;
            balance = newBalance;
        }
        finally {
            balanceChangeLock.unlock();
        }
    }

    /**
     Gets the current balance of the brokerage account.
     @return the current balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     Gets the current value of the brokerage account.
     @return the current value
     */
    public double getValue() {
        double value = 0;
        for (Stock stock : portfolio.keySet()) {
            value += stock.viewPrice() * portfolio.get(stock);
        }
        return value;
    }

    /**
     Gets the remaining balance, stocks and quantity owned, and total value.
     @return a String containing the account statistics
     */
    public String view() {
        StringBuilder sb = new StringBuilder();
        sb.append("Balance: $").append(balance).append(" | ");
        sb.append("Shares owned: ");
        for (Stock stock : portfolio.keySet()) {
            sb.append("Symbol: ").append(stock.getSymbol()).append(" Qty: ").append(portfolio.get(stock)).append(" | ");
        }
        sb.append("Total Value: $").append(this.getValue());
        return sb.toString();
    }
}