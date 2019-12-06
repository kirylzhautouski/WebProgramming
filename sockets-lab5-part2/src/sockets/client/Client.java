package sockets.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sockets.server.Server;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Class that connects to Server and receives a random line
 */
public class Client {

    /**
     * Client logger
     */
    private static final Logger CLIENT_LOGGER = LogManager.getLogger(Client.class.getName());

    /**
     * Client request to server
     */
    public static final String REQUIRE_LINE = "REQUIRE_LINE";

    /**
     * An entry point of an application
     */
    public void runClient(String hostname) {
        CLIENT_LOGGER.info("Starting client");

        if (hostname == null) {
            hostname = "localhost";
        }

        CLIENT_LOGGER.info("Creating client socket");

        try (SocketChannel channel = SocketChannel.open(new InetSocketAddress(hostname, Server.PORT))) {
            ByteBuffer bufferToWrite = ByteBuffer.wrap(REQUIRE_LINE.getBytes());

            channel.write(bufferToWrite);

            ByteBuffer bufferToRead = ByteBuffer.allocate(1024);

            int bytesRead = channel.read(bufferToRead);

            bufferToRead.flip();

            byte[] bytes = new byte[bytesRead];
            bufferToRead.get(bytes);

            String message = new String(bytes);
            System.out.println(message);
        } catch (IOException ex) {
            CLIENT_LOGGER.warn(ex.getMessage());
        }

    }

}
