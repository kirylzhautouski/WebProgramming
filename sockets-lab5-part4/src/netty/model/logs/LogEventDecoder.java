package netty.model.logs;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

public class LogEventDecoder extends MessageToMessageDecoder<DatagramPacket> {

    @Override
    protected void decode(ChannelHandlerContext ctx, DatagramPacket datagramPacket, List<Object> out) throws Exception {
        ByteBuf data = datagramPacket.content();

        int idx = data.indexOf(0, data.readableBytes(),
            LogEvent.SEPARATOR);

        String filename = data.slice(0, idx)
            .toString(CharsetUtil.UTF_8);

        int terminatorIdx = data.indexOf(0, data.readableBytes(), LogEvent.TERMINATOR);
        String logMsg = data.slice(idx + 1,
            terminatorIdx - idx - 1).toString(CharsetUtil.UTF_8);

        LogEvent event = new LogEvent(datagramPacket.sender(), filename, logMsg);

        out.add(event);
    }
}
