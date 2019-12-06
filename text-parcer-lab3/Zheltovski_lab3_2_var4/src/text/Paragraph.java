package text;

/**
 * Class representing paragraph in the text
 */
public class Paragraph implements TextUnit {

    /** String paragraph representation */
    public static final String PARAGRAPH_STRING = "\n";

    /**
     * Builds String representation of Paragraph
     * @return
     */
    @Override
    public String build() {
        return PARAGRAPH_STRING;
    }
}
