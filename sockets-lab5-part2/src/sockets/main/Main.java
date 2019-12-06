package sockets.main;

import sockets.client.Client;
import sockets.server.Server;

public class Main {


    public static final String FILENAME = "input.txt";

    public static void main(String[] args) {

        Server server = new Server(FILENAME);
        Runnable serverRunnable = server::startServer;

        Client client1 = new Client();
        Client client2 = new Client();

        Runnable clientRunnable1 = () -> client1.runClient(null);
        Runnable clientRunnable2 = () -> client2.runClient(null);

        Thread serverThread = new Thread(serverRunnable);

        Thread clientThread1 = new Thread(clientRunnable1);
        Thread clientThread2 = new Thread(clientRunnable2);

        serverThread.start();

        clientThread1.start();
        clientThread2.start();

    }


}
