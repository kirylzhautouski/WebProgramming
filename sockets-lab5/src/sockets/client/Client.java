package sockets.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sockets.server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Class that connects to Server and receives a random line
 */
public class Client {

    /**
     * Client logger
     */
    private static final Logger CLIENT_LOGGER = LogManager.getLogger(Client.class.getName());

    public static final String REQUIRE_LINE = "REQUIRE_LINE";

    /**
     * An entry point of an application
     * @param args
     *        Command-line arguments
     */
    public static void main(String[] args) {
        CLIENT_LOGGER.info("Starting client");

        String hostname;
        if (args.length > 0) {
            hostname = args[0];
        } else {
            hostname = "localhost";
        }

        CLIENT_LOGGER.info("Creating client socket");

        try (Socket socket = new Socket(hostname, Server.PORT);
             PrintStream printStream = new PrintStream(socket.getOutputStream());
             BufferedReader consoleBufferedReader = new BufferedReader(new InputStreamReader(System.in))) {

            while (true) {
                System.out.print("Do you want to read line from server: ");

                if (consoleBufferedReader.readLine().equals("yes")) {
                    printStream.println(REQUIRE_LINE);

                    CLIENT_LOGGER.info("Reading line received from server");

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    String receivedLine = bufferedReader.readLine();

                    System.out.println(receivedLine);
                } else {
                    break;
                }
            }
        } catch (IOException ex) {
            CLIENT_LOGGER.warn(ex.getMessage());
        }

    }

}
