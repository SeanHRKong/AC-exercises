import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {

    private Socket clientSocket;
    private ServerSocket serverSocket;
    private BufferedReader inputBufferedReader;
    private BufferedWriter outputBufferedWriter;

    public WebServer(int port) {

        try {

            setupSocketStreams(port);



            while (true) {

                try {

                    lineInterpreter(inputBufferedReader.readLine());

                } catch (IOException e) {

                    System.out.println("Receiving error: " + e.getMessage());

                }

            }

        } catch (IOException e) {

            System.out.println(e.getMessage());

        } finally {

            close();
        }

    }


    public static void main(String args[]) {

        try {

            new WebServer(42069);

        } catch (NumberFormatException e) {

            System.out.println("Invalid port number");
        }

    }


    public void setupSocketStreams(int port) throws IOException {

        inputBufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        outputBufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
    }


    public void close() {

        try {

            if (clientSocket != null) {
                System.out.println("Closing client connection");
                clientSocket.close();
            }

        } catch (IOException e) {

            System.out.println("Error closing connection: " + e.getMessage());

        }

    }


    public void lineInterpreter() throws IOException {

        String input = inputBufferedReader.readLine();

        String path = input.substring(input.indexOf("/") + 1) + 1;


    }
}
