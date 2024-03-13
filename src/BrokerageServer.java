import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 A server that executes the Simple Stock Trading Protocol.
 */
public class BrokerageServer
{
    public static void main(String[] args) throws IOException
    {
        final int ACCOUNTS_LENGTH = 10;
        final int STOCKS_COUNT = 3;
        Brokerage brokerage = new Brokerage(ACCOUNTS_LENGTH, STOCKS_COUNT);

        final int SBAP_PORT = 8888;
        ServerSocket server = new ServerSocket(SBAP_PORT);
        System.out.println("Waiting for clients to connect...");

        // maintain the server running while clients connect
        while (true)
        {
            Socket s = server.accept();
            System.out.println("Client connected.");
            BrokerageService service = new BrokerageService(s, brokerage);
            Thread t = new Thread(service);
            t.start();
        }
    }
}