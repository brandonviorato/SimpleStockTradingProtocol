import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 Executes Simple Stock Trading Protocol commands
 from a socket.
 */
public class BrokerageService implements Runnable
{
    private Socket s;
    private Scanner in;
    private PrintWriter out;
    private Brokerage brokerage;

    /**
     Constructs a service object that processes commands
     from a socket for a brokerage.
     @param aSocket the socket
     @param aBrokerage the brokerage
     */
    public BrokerageService(Socket aSocket, Brokerage aBrokerage)
    {
        s = aSocket;
        brokerage = aBrokerage;
    }

    // establishes a connection between the server and client
    public void run()
    {
        try
        {
            try
            {
                in = new Scanner(s.getInputStream());
                out = new PrintWriter(s.getOutputStream());
                doService();
            }
            finally
            {
                s.close();
            }
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }

    /**
     Executes all commands until the QUIT command or the
     end of input.
     */
    public void doService() throws IOException
    {
        while (true)
        {
            if (!in.hasNext()) { return; }
            String command = in.next();
            if (command.equals("QUIT")) { return; }
            else { executeCommand(command); }
        }
    }

    /**
     Executes a single command.
     @param command the command to execute
     */
    public void executeCommand(String command)
    {

        if (command.equals("DEPOSIT"))
        {
            int account = in.nextInt();
            double amount = in.nextDouble();
            brokerage.deposit(account, amount);
            out.println(account + " " + brokerage.getBalance(account));
            out.flush();
        }
        else if (command.equals("WITHDRAW"))
        {
            int account = in.nextInt();
            double amount = in.nextDouble();
            brokerage.withdraw(account, amount);
            out.println(account + " " + brokerage.getBalance(account));
            out.flush();
        }
        else if (command.equals("BUY"))
        {
            int account = in.nextInt();
            String symbol = in.next();
            int quantity = in.nextInt();
            brokerage.buy(account, symbol, quantity);
            out.println(account + " " + brokerage.getBalance(account));
            out.flush();
        }
        else if (command.equals("SELL"))
        {
            int account = in.nextInt();
            String symbol = in.next();
            int quantity = in.nextInt();
            brokerage.sell(account, symbol, quantity);
            out.println(account + " " + brokerage.getBalance(account));
            out.flush();
        }
        else if (command.equals("PRICE"))
        {
            String symbol = in.next();
            double price = brokerage.getPrice(symbol);
            out.println(symbol + ": $" + price);
            out.flush();
        }
        else if (command.equals("VIEW"))
        {
            int account = in.nextInt();
            String message = brokerage.getPortfolio(account);
            out.println(message);
            out.flush();
        }
        else if (!command.equals("BALANCE"))
        {
            out.println("Invalid command");
            out.flush();
            return;
        }
    }
}