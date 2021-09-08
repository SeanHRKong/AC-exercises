import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private PrintWriter out;
    private BufferedReader in;
    private Socket clientSocket;
    private ServerSocket serverSocket;
    private BufferedReader input;

    public static void main(String args[]) {

        Server server = new Server();
        server.start();
    }

    private void start() {

        try {

            System.out.println("Awaiting connection...");

            streamBuilder();

            while(clientSocket.isBound()) {

                send();

                receive();
            }

            System.out.println("Closing connection.");
            in.close();
            out.close();
            input.close();
            clientSocket.close();
            serverSocket.close();

        } catch (NumberFormatException e) {

            System.err.println("Error: Invalid port!");

        } catch (IOException e) {

            System.err.println("Error: Network failure: " + e.getMessage());
        }
    }

    private void streamBuilder() throws IOException {

        //1 - stuff
        int portNumber = 42069;

        //2 - bind to local port and block while wait for client
        serverSocket = new ServerSocket(portNumber);
        clientSocket = serverSocket.accept();

        //3 - setup input and output streams
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        System.out.println("Connection established!");
    }

    //4 - READ/WRITE
    private void receive() throws IOException {

        System.out.println("Client: " + in.readLine());
    }

    private void send() throws IOException {

        input = new BufferedReader(new InputStreamReader(System.in));

        String message = input.readLine();

        out.println(message);
    }
}
