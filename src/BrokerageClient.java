import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 This program tests the brokerage server.
 */
public class BrokerageClient
{
    public static void main(String[] args) throws IOException
    {
        final int SBAP_PORT = 8888;

        // localhost means we are connecting to our own device
        // that we are currently running on (loop back to ourselves)
        // localhost is a name for IP address 127.0.0.1

        try (Socket s = new Socket("localhost", SBAP_PORT))
        {
            InputStream instream = s.getInputStream();
            OutputStream outstream = s.getOutputStream();
            Scanner in = new Scanner(instream);
            PrintWriter out = new PrintWriter(outstream);
            // test account 1 deposit 2000
            String command = "DEPOSIT 1 2000";
            System.out.println("Sending: " + command);
            out.print(command + "\n");
            out.flush();
            String response = in.nextLine();
            System.out.println("Receiving: " + response);
            // purchase 3 shares of apple
            command = "BUY 1 AAPL 3";
            System.out.println("Sending: " + command);
            out.print(command + "\n");
            out.flush();
            response = in.nextLine();
            System.out.println("Receiving: " + response);
            // view account 1 portfolio
            command = "VIEW 1";
            System.out.println("Sending: " + command);
            out.print(command + "\n");
            out.flush();
            response = in.nextLine();
            System.out.println("Receiving: " + response);
            // check price of aaple
            command = "PRICE AAPL";
            System.out.println("Sending: " + command);
            out.print(command + "\n");
            out.flush();
            response = in.nextLine();
            System.out.println("Receiving: " + response);
            // see account 1 adjusted portfolio value
            command = "VIEW 1";
            System.out.println("Sending: " + command);
            out.print(command + "\n");
            out.flush();
            response = in.nextLine();
            System.out.println("Receiving: " + response);
            // sell 3 shares of apple on account 1
            command = "SELL 1 AAPL 3";
            System.out.println("Sending: " + command);
            out.print(command + "\n");
            out.flush();
            response = in.nextLine();
            System.out.println("Receiving: " + response);
            // see account 1 adjusted portfolio value
            command = "VIEW 1";
            System.out.println("Sending: " + command);
            out.print(command + "\n");
            out.flush();
            response = in.nextLine();
            System.out.println("Receiving: " + response);

            command = "DEPOSIT 3 5000";
            System.out.println("Sending: " + command);
            out.print(command + "\n");
            out.flush();
            response = in.nextLine();
            System.out.println("Receiving: " + response);

            command = "BUY 3 AAPL 5";
            System.out.println("Sending: " + command);
            out.print(command + "\n");
            out.flush();
            response = in.nextLine();
            System.out.println("Receiving: " + response);

            command = "BUY 3 MSFT 3";
            System.out.println("Sending: " + command);
            out.print(command + "\n");
            out.flush();
            response = in.nextLine();
            System.out.println("Receiving: " + response);

            command = "VIEW 3";
            System.out.println("Sending: " + command);
            out.print(command + "\n");
            out.flush();
            response = in.nextLine();
            System.out.println("Receiving: " + response);

            command = "WITHDRAW 1 500";
            System.out.println("Sending: " + command);
            out.print(command + "\n");
            out.flush();
            response = in.nextLine();
            System.out.println("Receiving: " + response);
            // test invalid command
            command = "WIHTDRAW 1 500";
            System.out.println("Sending: " + command);
            out.print(command + "\n");
            out.flush();
            response = in.nextLine();
            System.out.println("Receiving: " + response);
            // test invalid stock NOTE: DOES NOT CRASH SERVER, WILL STILL ACCEPT CLIENTS
            command = "BUY 1 SBUX 3";
            System.out.println("Sending: " + command);
            out.print(command + "\n");
            out.flush();
            response = in.nextLine();

            System.out.println("Receiving: " + response);
            command = "QUIT";
            System.out.println("Sending: " + command);
            out.print(command + "\n");
            out.flush();
        }
    }
}