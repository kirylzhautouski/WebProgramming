package netty.model.response;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import netty.model.Utils;

import java.util.List;

/**
 * Decoder for ResponseData
 */
public class ResponseDecoder extends ReplayingDecoder<ResponseData> {

    /**
     * From bytes to ResponseData objects
     * @param ctx ChannelHandlerContext
     * @param in ByteBuf
     * @param out List of objects
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        ResponseData responseData = new ResponseData();

        int lineLength = in.readInt();
        responseData.setRandomLineFromText(in.readCharSequence(lineLength, Utils.CHARSET).toString());

        out.add(responseData);
    }
}
