package db.exceptions;

/**
 * An exception that is thrown by JdbcConnector
 */
public class JDBCConnectionException extends Exception {

    /**
     * Constructs JDBCConnectionException object
     * @param message
     *        Exception details message
     * @param cause
     *        Exception that caused this excpetion
     */
    public JDBCConnectionException(String message, Exception cause) {
        super(message, cause);
    }

    /**
     * Constructs JDBCConnectionException object
     * @param message
     *        Exception details message
     */
    public JDBCConnectionException(String message) {
        super(message);
    }

    /**
     * Constructs JDBCConnectionException object
     */
    public JDBCConnectionException() {

    }

}
