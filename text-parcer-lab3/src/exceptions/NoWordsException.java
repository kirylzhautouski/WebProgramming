package exceptions;

import localization.LocaleManager;

public class NoWordsException extends Exception {

    /**
     * Constructor with specified string
     * @param message string
     */
    public NoWordsException(String message) {
        super(LocaleManager.getLocalizedString(LocaleManager.NO_WORDS) + ", " + message);
    }

    /**
     * Constructor with specified string and exception
     * @param message string
     * @param cause Throwable
     */
    public NoWordsException(String message, Throwable cause){
        super(LocaleManager.getLocalizedString(LocaleManager.NO_WORDS) + ", " + message, cause);
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
