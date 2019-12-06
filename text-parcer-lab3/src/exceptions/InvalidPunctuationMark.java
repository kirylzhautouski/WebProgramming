package exceptions;

import localization.LocaleManager;

public class InvalidPunctuationMark extends Exception {

    /**
     * Constructor with specified string
     * @param message string
     */
    public InvalidPunctuationMark(String message) {
        super(LocaleManager.getLocalizedString(LocaleManager.INVALID_PUNCTUATION_MARK) + ", " + message);
    }

    /**
     * Constructor with specified string and exception
     * @param message string
     * @param cause Throwable
     */
    public InvalidPunctuationMark(String message, Throwable cause){
        super(LocaleManager.getLocalizedString(LocaleManager.INVALID_PUNCTUATION_MARK) + ", " + message, cause);
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
