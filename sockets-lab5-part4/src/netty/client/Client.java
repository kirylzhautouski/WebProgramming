package netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import netty.model.logs.LogEventDecoder;
import netty.model.request.RequestEncoder;
import netty.model.response.ResponseDecoder;

import java.net.InetSocketAddress;

/**
 * Client that requests Server to get random line from string
 */
public class Client {

    /** Request to server */
    public static final String REQUIRE_LINE = "REQUIRE_LINE";

    /** Server hostname */
    private static final String HOSTNAME = "localhost";

    /** Server port */
    private static final int PORT = 8080;

    /**
     * An entry point to the client application, starts client
     * @param args
     *        Command-line arguments
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b1 = new Bootstrap();
            b1.group(workerGroup);
            b1.channel(NioSocketChannel.class);
            b1.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new RequestEncoder(), new ResponseDecoder(), new ClientHandler());
                }
            });

            ChannelFuture f = b1.connect(HOSTNAME, PORT).sync();

            Bootstrap b2 = new Bootstrap();
            b2.group(workerGroup);
            b2.channel(NioDatagramChannel.class);
            b2.option(ChannelOption.SO_BROADCAST, true);
            b2.handler( new ChannelInitializer<Channel>() {
                @Override
                protected void initChannel(Channel channel)
                    throws Exception {
                    ChannelPipeline pipeline = channel.pipeline();
                    pipeline.addLast(new LogEventDecoder());
                    pipeline.addLast(new ClientLogEventHandler());
                }
            })
            .localAddress(new InetSocketAddress(8888));

            Channel channel = b2.bind().sync().channel();
            f.channel().closeFuture().sync();
            channel.closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }


}
