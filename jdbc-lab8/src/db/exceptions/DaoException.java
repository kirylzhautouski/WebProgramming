package db.exceptions;

/**
 * Exception that is thrown by various Dao methods
 */
public class DaoException extends Exception {

    /**
     * Constructs DaoException object
     * @param message
     *        Exception details message
     * @param cause
     *        Exception that caused this excpetion
     */
    public DaoException(String message, Exception cause) {
        super(message, cause);
    }

    /**
     * Constructs DaoException object
     * @param message
     *        Exception details message

     */
    public DaoException(String message) {
        super(message);
    }

    /**
     * Constructs DaoException object
     */
    public DaoException() {

    }

}
