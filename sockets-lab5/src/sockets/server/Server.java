package sockets.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sockets.client.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

/**
 * Class that holds server socket, accepts clients and sends them a random line from file
 */
public class Server {

    /**
     * Socket port
     */
    public static final int PORT = 8029;

    public static final String BAD_REQUEST = "BAD_REQUEST";

    /**
     * Server logger, which writes to all.log nad server.log
     */
    private static final Logger SERVER_LOGGER = LogManager.getLogger(Server.class.getName());

    /**
     * Name of file for retrieving lines
     */
    private static final String FILENAME = "input.txt";

    /**
     * An entry point of an application
     * @param args
     *        Command-line arguments
     */
    public static void main(String[] args) {
        SERVER_LOGGER.info("Server launching");

        try {
            SERVER_LOGGER.info("Loading lines from file");
            List<String> lines = loadLinesFromFile(FILENAME);

            SERVER_LOGGER.info("Creating server socket");
            ServerSocket serverSocket = new ServerSocket(PORT);

            while (true) {
                SERVER_LOGGER.info("Accepting client socket");
                Socket clientSocket = serverSocket.accept();

                SERVER_LOGGER.info("Client " + clientSocket.getInetAddress().getHostName() + " connected");

                SERVER_LOGGER.info("Starting client thread");
                Thread clientThread = new Thread(new ProcessClientRunnable(clientSocket, lines));
                clientThread.start();
            }

        } catch (IOException ex) {
            SERVER_LOGGER.warn(ex.getMessage());
        }

    }

    /**
     * Retrieves all lines from file
     * @param filename
     *        Name of file to read from
     * @return List of lines
     * @throws IOException
     */
    private static List<String> loadLinesFromFile(String filename) throws IOException {
        return Files.readAllLines(Paths.get(filename));
    }

    /**
     * Class that implements Runnable and used to send a random line to client
     */
    private static class ProcessClientRunnable implements Runnable {

        /**
         * Client socket
         */
        private Socket socket;

        /**
         * Lines of text
         */
        private List<String> lines;

        /**
         * Initializes a newly created class with the given values
         * @param socket
         *        Client socket
         * @param lines
         *        Lines of text
         */
        public ProcessClientRunnable(Socket socket, List<String> lines) {
            this.socket = socket;
            this.lines = lines;
        }

        /**
         * Method used to process clients. Sends a random line from file.
         */
        @Override
        public void run() {
            Random random = new Random();

            try (PrintStream outputStream = new PrintStream(socket.getOutputStream());
                 BufferedReader inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                String request = inputStream.readLine();
                while (request != null) {
                    if (request.equals(Client.REQUIRE_LINE)) {
                        int lineIndex = random.nextInt(lines.size());

                        outputStream.println(lines.get(lineIndex));
                    } else {
                        outputStream.println(Server.BAD_REQUEST);
                    }

                    request = inputStream.readLine();
                }
            } catch (IOException ex) {
                SERVER_LOGGER.warn(ex.getMessage());
            } finally {
                try {
                    socket.close();
                } catch (IOException ex) {
                    SERVER_LOGGER.warn(ex.getMessage());
                }
            }

        }
    }

}
