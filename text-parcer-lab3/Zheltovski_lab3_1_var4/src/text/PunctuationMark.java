package text;

/**
 * Enum class representing punctuation mark in the text
 */
public enum PunctuationMark implements TextUnit {

    POINT('.'), EXCLAMATION_MARK('!'), QUESTION_MARK('?'), COMMA(','), COLON(':'), SEMICOLON(';');

    /** Char for punctuation mark */
    private char punctuationMark;

    /**
     * Creates a initializes PunctuationMark class
     * @param punctuationMark
     *        Char for punctuation mark
     */
    PunctuationMark(char punctuationMark) {
        this.punctuationMark = punctuationMark;
    }

    /**
     * Builds String representation for PunctuationMark
     * @return String representation for PunctuationMark
     */
    @Override
    public String build() {
        return this.toString();
    }

    /**
     * Returns String representation for PunctuationMark
     * @return String representation for PunctuationMark
     */
    @Override
    public String toString() {
        return Character.toString(punctuationMark);
    }
}
