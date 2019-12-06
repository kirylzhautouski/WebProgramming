package text.parsers;

import exceptions.InvalidPunctuationMark;
import org.apache.log4j.Logger;
import text.Code;
import text.Paragraph;
import text.Sentence;
import text.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class used for parsing text
 */
public class TextParser implements Parser {

    private static final Logger LOGGER = Logger.getLogger("textParser");

    /** Sentence parser */
    private SentenceParser sentenceParser = new SentenceParser();

    /** Code parser */
    private CodeParser codeParser = new CodeParser();

    /** Paragraph parser */
    private ParagraphParser paragraphParser = new ParagraphParser();

    /**
     * Parses textString for Text
     * @param textString
     *        String to be parsed
     * @return Text - text
     */
    @Override
    public Text parse(String textString) {
        LOGGER.info("Start parsing");

        Text result = new Text();

        String[] paragraphs = textString.split(Paragraph.PARAGRAPH_STRING);

        for (String paragraph : paragraphs) {
            LOGGER.info("Parsed paragraph");

            Pattern sentencePattern = TextParser.compilePattern(Sentence.getSentencePatternString());
            Pattern codePattern = TextParser.compilePattern(Code.getCodePatternString());

            Matcher matcherSentence = sentencePattern.matcher(paragraph);
            while (matcherSentence.find()) {
                String sentence = matcherSentence.group();

                Matcher matcherCode = codePattern.matcher(sentence);
                while (matcherCode.find()) {
                    result.addUnit(codeParser.parse(matcherCode.group().trim()));
                }

                sentence = codePattern.matcher(sentence).replaceAll("").trim();
                if (!sentence.isEmpty()) {
                    result.addUnit(sentenceParser.parse(sentence));
                }
            }

            result.addUnit(paragraphParser.parse(Paragraph.PARAGRAPH_STRING));
        }

        LOGGER.info("End parsing");

        return result;
    }

    /**
     * Helper method to compile patterns from strings of regular expressions
     * @param patternString
     * @return
     */
    private static Pattern compilePattern(String patternString) {
        return Pattern.compile(patternString);
    }
}
