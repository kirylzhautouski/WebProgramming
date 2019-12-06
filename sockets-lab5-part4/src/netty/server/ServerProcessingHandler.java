package netty.server;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import netty.model.request.RequestData;
import netty.model.response.ResponseData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Random;

/**
 * Processes clients
 */
public class ServerProcessingHandler extends ChannelInboundHandlerAdapter {



    /**
     * Lines to choose from
     */
    private List<String> lines;

    /**
     * Used for choosing a line
     */
    private Random random;

    /**
     * Server logger
     */
    private Logger logger;
    /**
     * Initializes ServerProcessingHandler
     * @param lines Lines
     * @param random Random
     */
    public ServerProcessingHandler(List<String> lines, Random random, Logger logger) {
        this.lines = lines;
        this.random = random;

        this.logger = logger;

        logger.info("ServerProcessingHandler constructed");
    }

    /**
     * On channel register
     * @param ctx ChannelHandlerContext
     * @throws Exception
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        logger.info(ctx.channel().remoteAddress() + " registered");
    }

    /**
     * On channel unregister
     * @param ctx ChannelHandlerContext
     * @throws Exception
     */
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        logger.info(ctx.channel().remoteAddress() + " unregistered");
    }

    /**
     * On handle add
     * @param ctx ChannelHandlerContext
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        logger.info("Handler added");
    }

    /**
     * On handle remove
     * @param ctx ChannelHandlerContext
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        logger.info("Handler removed");
    }

    /**
     * Reads client request and sends them a line
     * @param ctx ChannelHandlerContext
     * @param msg msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("Reading request");

        RequestData requestData = (RequestData)msg;

        ResponseData responseData = new ResponseData();
        responseData.setRandomLineFromText(lines.get(random.nextInt(lines.size())));

        ChannelFuture future = ctx.writeAndFlush(responseData);

        future.addListener(ChannelFutureListener.CLOSE);

        System.out.println(requestData.getRequest());
    }
}
