package text;

/**
 * Class representing words in the text
 */
public class Word implements TextUnit {

    /** String for the word */
    private String word;

    /**
     * Creates and initializes Word
     * @param word
     *        Word
     */
    public Word(String word) {
        this.word = word;
    }

    /**
     * Builds String representation of Word
     * @return String representation of Word
     */
    @Override
    public String build() {
        return word;
    }

}
