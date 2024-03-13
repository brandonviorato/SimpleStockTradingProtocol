import java.util.*;
public class Main {
    public static void main(String[] args) {
        Stock aapl = new Stock("AAPL");
        for (int i = 0; i <= 10; i++) {
            aapl.getPrice();
            System.out.println(aapl);
        }
    }
}
