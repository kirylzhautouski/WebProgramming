package netty.model.request;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import netty.model.Utils;

/**
 * RequestData encoder
 */
public class RequestEncoder extends MessageToByteEncoder<RequestData> {

    /**
     * Encodes RequestData object into bytes
     * @param ctx ChannelHandlerContext
     * @param msg RequestData
     * @param out Bytes
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, RequestData msg, ByteBuf out) throws Exception {
        out.writeCharSequence(msg.getRequest(), Utils.CHARSET);
    }
}
