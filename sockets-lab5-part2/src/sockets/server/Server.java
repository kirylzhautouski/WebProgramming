package sockets.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sockets.client.Client;
import sockets.utils.Utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Class that holds server socket, accepts clients and sends them a random line from file
 */
public class Server {

    /**
     * Socket port
     */
    public static final int PORT = 8029;

    /**
     * Server response on invalid request
     */
    public static final String BAD_REQUEST = "BAD_REQUEST";

    /**
     * Server logger, which writes to all.log nad server.log
     */
    private static final Logger SERVER_LOGGER = LogManager.getLogger(Server.class.getName());

    /**
     * Lines of text
     */
    private List<String> lines;

    /**
     * Selector for accepting clients, reading from client channels and writing to them
     */
    private Selector selector;

    /**
     * Contains messages from clients
     */
    private Map<SocketChannel, List<byte[]>> currentPartialMessagesForChannels;

    /**
     * Full messages to send to clients
     */
    private Map<SocketChannel, ByteBuffer> messageToWriteForChannels;

    /**
     * Used for randoming integer - line form file
     */
    private Random random;

    /**
     * Creates and initializes Server object. Reads filename line by line
     * @param filename
     */
    public Server(String filename) {
        try {
            lines = loadLinesFromFile(filename);

            selector = Selector.open();
        } catch (IOException ex) {
            throw new IllegalArgumentException("Invalid filename", ex);
        }

        currentPartialMessagesForChannels = new HashMap<>();
        messageToWriteForChannels = new HashMap<>();

        random = new Random();
    }

    /**
     * Starts server
     */
    public void startServer() {
        SERVER_LOGGER.info("Server launching");

        try {
            SERVER_LOGGER.info("Loading lines from file");

            SERVER_LOGGER.info("Creating server socket");

            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

            serverSocketChannel.bind(new InetSocketAddress(Server.PORT));

            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            SERVER_LOGGER.info("Server started");
            while (true) {
                selector.select();

                Iterator keys = selector.selectedKeys().iterator();
                while (keys.hasNext()) {
                    SelectionKey key = (SelectionKey)keys.next();

                    keys.remove();

                    if (!key.isValid()) {
                        continue;
                    }

                    if (key.isAcceptable()) {
                        accept(key);
                    }

                    if (key.isReadable()) {
                        read(key);
                    }

                    if (key.isWritable()) {
                        write(key);
                    }
                }
            }

        } catch (IOException ex) {
            SERVER_LOGGER.warn(ex.getMessage());
        }

    }

    /**
     * Retrieves all lines from file
     * @return List of lines
     * @throws IOException
     */
    private List<String> loadLinesFromFile(String filename) throws IOException {
        return Files.readAllLines(Paths.get(filename));
    }

    private void accept(SelectionKey key) throws IOException {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel)key.channel();

        SocketChannel channel = serverSocketChannel.accept();
        channel.configureBlocking(false);

        currentPartialMessagesForChannels.put(channel, new ArrayList<>());
        channel.register(selector, SelectionKey.OP_READ);
    }

    private void read(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel)key.channel();

        ByteBuffer buffer = ByteBuffer.allocate(512);
        int bytesRead = channel.read(buffer);

        if (bytesRead != -1) {
            buffer.flip();

            byte[] data = new byte[bytesRead];
            buffer.get(data);

            List<byte[]> byteArrays = currentPartialMessagesForChannels.get(channel);
            byteArrays.add(data);

            if (Utils.countBytes(byteArrays) == Client.REQUIRE_LINE.getBytes().length) {
                // means we received the last part, check full message
                // if ok then channel is ready to write, register it and form message for it

                currentPartialMessagesForChannels.remove(channel);
                byte[] messageBytes = Utils.joinArrays(byteArrays);

                String message = new String(messageBytes);
                System.out.println(message);

                if (message.equals(Client.REQUIRE_LINE)) {
                    ByteBuffer bufferToWrite = ByteBuffer.wrap(lines.get(random.nextInt(lines.size())).getBytes());

                    messageToWriteForChannels.put(channel, bufferToWrite);

                    channel.register(selector, SelectionKey.OP_WRITE);
                } else {
                    channel.close();
                }
            }
        }
    }

    /**
     * Handle write to client chanel
     * @param key
     *        SelectionKey with channel ready to write
     * @throws IOException
     *         Channel write
     */
    private void write(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel)key.channel();

        // write message for client
        channel.write(messageToWriteForChannels.get(channel));

        messageToWriteForChannels.remove(channel);

        channel.close();
    }

}
