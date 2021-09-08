package org.academiadecodigo.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {

    private Socket clientSocket;
    private ServerSocket serverSocket;
    private BufferedReader inputBufferedReader;
    private CopyOnWriteArrayList<Socket> clientList;


    public Server(int port) {

        try {

            System.out.println("Binding to port " + port);
            serverSocket = new ServerSocket(port);

            System.out.println("Server started: " + serverSocket);

            System.out.println("Waiting for clients");
            clientSocket = serverSocket.accept();

            System.out.println("Client accepted: " + clientSocket);
            setupSocketStream();

            while (true) {

                try {

                    String line = inputBufferedReader.readLine();

                    if (line == null || line.equals("/quit")) {

                        System.out.println("Client closed, exiting");
                        break;

                    }

                    System.out.println(line);

                } catch (IOException ex) {

                    System.out.println("Receiving error: " + ex.getMessage());

                }

            }


        } catch (IOException ioe) {

            System.out.println(ioe.getMessage());

        } finally {

            close();

        }

    }



    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Usage: java Server [port]");
            System.exit(1);
        }

        try {
            new Server(Integer.parseInt(args[0]));

        } catch (NumberFormatException ex) {
            System.out.println("Invalid port number " + args[0]);
        }

    }



    public void setupSocketStream() throws IOException {
        inputBufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }



    public void close() {

        try {

            if (clientSocket != null) {
                System.out.println("Closing client connection");
                clientSocket.close();
            }

            if (serverSocket != null) {
                System.out.println("Closing server socket");
                serverSocket.close();
            }


        } catch (IOException ex) {

            System.out.println("Error closing connection: " + ex.getMessage());

        }

    }
}
