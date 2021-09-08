package org.academiadecodigo.bootcamp.cuncurrentchat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ReadFromConsoleSendToServer implements Runnable{

    private Socket clientSocket;
    private String name;


    public ReadFromConsoleSendToServer(Socket clientSocket, String name) {
        this.clientSocket = clientSocket;
        this.name = name;
    }


    public void readFromConsoleSendToServer() throws IOException {

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Waiting for user message.");
        String message = consoleReader.readLine();

        PrintWriter serverWrite = new PrintWriter(clientSocket.getOutputStream(), true);
        serverWrite.println(name + ": " + message);
    }

    @Override
    public void run() {
        try {
            while(true) {
                readFromConsoleSendToServer();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
