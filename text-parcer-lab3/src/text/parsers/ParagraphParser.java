package text.parsers;

import text.Paragraph;
import text.TextUnit;

/**
 * Class used for parsing paragraphs
 */
public class ParagraphParser implements Parser {

    /**
     * Parses Paragraph from textUnit
     * @param textUnit
     *        String to be parsed
     * @return Paragraph - paragraph
     */
    @Override
    public TextUnit parse(String textUnit) {
        return new Paragraph();
    }
}
