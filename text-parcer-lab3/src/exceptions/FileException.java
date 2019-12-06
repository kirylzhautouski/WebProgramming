package exceptions;

import localization.LocaleManager;

public class FileException extends Exception {

    /**
     * Constructor with specified string
     * @param message string
     */
    public FileException(String message) {
        super(LocaleManager.getLocalizedString(LocaleManager.INVALID_FILE) + ", " + message);
    }

    /**
     * Constructor with specified string and exception
     * @param message string
     * @param cause Throwable
     */
    public FileException(String message, Throwable cause){
        super(LocaleManager.getLocalizedString(LocaleManager.INVALID_FILE) + ", " + message, cause);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }

}
