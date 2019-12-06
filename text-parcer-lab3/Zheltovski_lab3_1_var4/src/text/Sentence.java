package text;


import java.util.Arrays;
import java.util.List;

/**
 * Class representing sentence in the text
 */
public class Sentence extends BaseComplexTextUnit {

    /**
     * List of sentence end punctuation marks
     */
    public static final List<PunctuationMark> SENTENCE_END_PUNCTUATION_MARKS = Arrays.asList(
        PunctuationMark.POINT, PunctuationMark.EXCLAMATION_MARK, PunctuationMark.QUESTION_MARK);

    /**
     * Builds String representation for Sentence. Concatenates words and punctuation marks
     * with the proper whitespaces.
     * @return String representation for Sentence
     */
    @Override
    public String build() {
        StringBuilder sentenceBuilder = new StringBuilder(units.get(0).build());

        for (int i = 1; i < units.size(); i++) {
            TextUnit unit = units.get(i);

            if (unit instanceof Word) {
                sentenceBuilder.append(" ");
            }

            sentenceBuilder.append(unit.build());
        }

        return sentenceBuilder.toString();
    }

    /**
     * Add unit to the sentence, sentence contains words and punctuation marks
     * @param unit
     *        TextUnit
     */
    public void addUnit(TextUnit unit) {
        if (!(unit instanceof Word || unit instanceof PunctuationMark))
            throw new IllegalArgumentException("Sentence may contain only Word and PunctuationMark");

        super.addUnit(unit);
    }

    /**
     * Makes sentence pattern string used for parsing using regular expressions
     * @return Sentence pattern string
     */
    public static String getSentencePatternString() {
        StringBuilder sentenceEndPunctuationMarksBuilder = new StringBuilder();
        for (PunctuationMark mark : Sentence.SENTENCE_END_PUNCTUATION_MARKS) {
            sentenceEndPunctuationMarksBuilder.append(mark.toString());
        }

        return String.format("([^%1$s])*([%1$s]|(\\z))",
            sentenceEndPunctuationMarksBuilder.toString());
    }

}
