package netty.client;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import netty.model.request.RequestData;
import netty.model.response.ResponseData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Sends request to server and reads response
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * ClientHandler Logger
     */
    private static final Logger CLIENT_HANDLER_LOGGER = LogManager.getLogger(ClientHandler.class.getName());

    /**
     * When channel is active send request
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        CLIENT_HANDLER_LOGGER.info("Sending request");

        RequestData msg = new RequestData();

        msg.setRequest(Client.REQUIRE_LINE);

        ctx.writeAndFlush(msg);
    }

    /**
     * When data is ready, read response
     * @param ctx ChannelHandlerContext
     * @param msg Response
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        CLIENT_HANDLER_LOGGER.info("Reading response");

        System.out.println(((ResponseData)msg).getRandomLineFromText());

        ctx.close();
    }
}
