package db.exceptions;

/**
 * An exception that is thrown by ConnectionPoolException
 */
public class ConnectionPoolException extends Exception {

    /**
     * Constructs ConnectionPoolException object
     * @param message
     *        Exception details message
     * @param cause
     *        Exception that caused this excpetion
     */
    public ConnectionPoolException(String message, Exception cause) {
        super(message, cause);
    }

    /**
     * Constructs ConnectionPoolException object
     * @param message
     *        Exception details message
     */
    public ConnectionPoolException(String message) {
        super(message);
    }

    /**
     * Constructs ConnectionPoolException object
     */
    public ConnectionPoolException() {

    }

}