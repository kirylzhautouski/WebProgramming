package netty.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import netty.model.logs.LogEvent;
import netty.model.logs.LogEventEncoder;
import netty.model.request.RequestDecoder;
import netty.model.response.ResponseEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

/**
 * Netty-based Server for processing client request
 */
public class Server {

    /**
     * ServerProcessingHandler logger
     */
    private static final Logger SERVER_PROCESSING_HANDLER_LOGGER = LogManager.getLogger(Server.class.getName());

    /**
     * Server port
     */
    private int port;

    /**
     * Creates a server on the given port
     * @param port
     */
    public Server(int port) {
        this.port = port;
    }

    private final static String LOG_FILE_NAME = "sockets.server.log";

    private Channel broadcastSender;

    /**
     * Start server
     * @param args Command-line args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        int port = args.length > 0
            ? Integer.parseInt(args[0])
          : 8080;

        new Server(port).run();
    }

    /**
     * Starts server
     * @throws Exception
     */
    private void run() throws Exception {
        List<String> lines = loadLinesFromFile("input.txt");
        Random random = new Random();

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b1 = new ServerBootstrap();
            b1.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                            .addLast(new RequestDecoder(), new ResponseEncoder(),
                                new ServerProcessingHandler(lines, random, SERVER_PROCESSING_HANDLER_LOGGER));
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture f1 = b1.bind(port).sync();

            Bootstrap b2 = new Bootstrap();
            b2.group(workerGroup)
                .channel(NioDatagramChannel.class)
                .option(ChannelOption.SO_BROADCAST, true)
                .handler(new LogEventEncoder(new InetSocketAddress("255.255.255.255", 8888)));

            broadcastSender = b2.bind(0).sync().channel();

            Thread t = new Thread(this::logsBroadcastRun);
            t.start();

            f1.channel().closeFuture().sync();
            broadcastSender.closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
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

    private void logsBroadcastRun() {
        try {
            File file = new File(LOG_FILE_NAME);

            long pointer = 0;

            for (;;) {
                long len = file.length();

                if (len < pointer) {
                    pointer = len;
                } else if (len > pointer) {
                    RandomAccessFile raf = new RandomAccessFile(file, "r");
                    raf.seek(pointer);
                    String line;
                    while ((line = raf.readLine()) != null) {
                        broadcastSender.writeAndFlush(new LogEvent(null,
                            LOG_FILE_NAME, line));
                    }
                    pointer = raf.getFilePointer();
                    raf.close();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.interrupted();
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


    }
}
