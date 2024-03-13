import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.*;
/**
 A Stock has a price that randomly changes when queried
 */
public class Stock {
    private String symbol;
    private double price;
    private Lock priceChangeLock;


    /**
     Constructs a stock with a random balance.
     @param symbol the stock symbol ex. Apple = AAPL
     */
    public Stock(String symbol) {
        this.symbol = symbol;
        priceChangeLock = new ReentrantLock();
        // generate price
        int max=250;
        int min=10;
        Random rand = new Random();
        price = rand.nextInt(max - min + 1) + min;
    }


    /**
     Gets the current price of the stock after randomization.
     @return the current price
     */
    public double getPrice() {
        priceChangeLock.lock();
        Random rand = new Random();
        try {
            // raise price
            if ((rand.nextInt(2 - 1 + 1) + 1) == 2) {
                price = rand.nextDouble((price * 1.3) - (price * 1.05) + 1) + (price * 1.05);
                price = (double) Math.round(price * 100) / 100;
            }
            // lower price
            else {
                price = rand.nextDouble((price * .98) - (price * .8) + 1) + (price * .8);
                price = (double) Math.round(price * 100) / 100;
            }
        }
        finally {
            priceChangeLock.unlock();
        }
        return price;
    }

    /**
     Gets the current price of the stock.
     @return the current price
     */
    public double viewPrice() {
        return price;
    }
    /**
     Gets the symbol of the stock.
     @return the stock symbol
     */
    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.symbol);
        sb.append(" Current Price: $");
        sb.append(this.price);
        return sb.toString();
    }
}
