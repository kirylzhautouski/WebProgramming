package netty.model.response;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import netty.model.Utils;

/**
 * Encoder for ResponseData
 */
public class ResponseEncoder extends MessageToByteEncoder<ResponseData> {

    /**
     * From object to bytes
     * @param ctx ChannelHandlerContext
     * @param msg ResponseData
     * @param out ByteBuf
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, ResponseData msg, ByteBuf out) throws Exception {
        out.writeInt(msg.getRandomLineFromText().length());
        out.writeCharSequence(msg.getRandomLineFromText(), Utils.CHARSET);
    }
}
