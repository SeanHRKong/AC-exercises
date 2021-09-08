package org.academiadecodigo.bootcamp.cuncurrentchat.client;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class Client {

    private Socket clientSocket;
    private String name;

    public Client(String name) {
        this.name = name;
    }

    public void connect(int port,String localhost) throws IOException {
        clientSocket = new Socket(localhost,port);
        System.out.println("Connected to " + localhost + " and " + port);

        Prompt prompt = new Prompt(System.in, System.out);

        StringInputScanner nameScanner = new StringInputScanner();
        nameScanner.setMessage("Name?\n");

        name = prompt.getUserInput(nameScanner);

    }

    public static void main(String[] args) {
        Client client = new Client("");

        try {
            client.connect(5051,"localhost");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread thread = new Thread(new ReadFromConsoleSendToServer(client.clientSocket, client.name));
        thread.start();

        Thread thread1 = new Thread(new ReadFromServerPrintToConsole(client.clientSocket));
        thread1.start();

    }
}

