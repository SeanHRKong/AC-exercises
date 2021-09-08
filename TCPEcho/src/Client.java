import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {

    private String hostName;
    private int portNumber;
    private PrintWriter out;
    private BufferedReader in;
    private BufferedReader input;
    private Socket clientSocket;

    public static void main(String args[]) {

        Client client = new Client();
        client.start();
    }

    private void start() {

            try {

                getUserInput();

                streamBuilder();

                while (clientSocket.isBound()) {

                    send();

                    receive();
                }

                System.out.println("Closing connection.");
                in.close();
                out.close();
                input.close();
                clientSocket.close();

            } catch (UnknownHostException e) {

                System.err.println("Error: Invalid host: " + this.hostName);

            } catch (NumberFormatException e) {

                System.err.println("Error: Invalid port!");

            } catch (SocketException e) {

                System.err.println("Error: Could not connect to server: " + this.hostName + ':' + this.portNumber);

            } catch (IOException e) {

                System.err.println("Error: Network failure: " + e.getMessage());
            }
    }

    private void getUserInput() throws IOException, NumberFormatException {

        //1 - get host and port
        input = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Hostname? ");
        hostName = input.readLine();

        System.out.print("Port? ");
        portNumber = Integer.parseInt(input.readLine());
    }

    private void streamBuilder() throws IOException {

        //2 - open client socket and block while connecting to server
        clientSocket = new Socket(hostName, portNumber);

        //3 - setup input and output streams
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        System.out.println("Connection established!");

        receive();
    }

    //4 - read/write
    private void receive() throws IOException {

        System.out.println("Server: " + in.readLine());
    }

    private void send() throws IOException {

        input = new BufferedReader(new InputStreamReader(System.in));

        String message = input.readLine();

        out.println(message);
    }
}
