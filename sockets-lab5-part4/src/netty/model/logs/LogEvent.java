package netty.model.logs;

import java.net.InetSocketAddress;

public final class LogEvent {

    public static final byte SEPARATOR = (byte) ':';
    public static final byte TERMINATOR = (byte) ';';

    private final InetSocketAddress source;
    private final String logfile;

    private final String msg;

    public LogEvent(InetSocketAddress source, String logfile, String msg) {
        this.source = source;
        this.logfile = logfile;
        this.msg = msg;
    }

    public InetSocketAddress getSource() {
        return source;
    }

    public String getLogfile() {
        return logfile;
    }

    public String getMsg() {
        return msg;
    }

}