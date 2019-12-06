package netty.model.request;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import netty.client.Client;
import netty.model.Utils;

import java.util.List;

/**
 * Decoder for RequestData
 */
public class RequestDecoder extends ReplayingDecoder<RequestData> {

    /**
     * Decodes from bytes to RequestData object
     * @param ctx ChannelHandlerContext
     * @param in Bytes
     * @param out List of objects
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        RequestData requestData = new RequestData();
        requestData.setRequest(in.readCharSequence(Client.REQUIRE_LINE.length(), Utils.CHARSET).toString());
        out.add(requestData);
    }
}
