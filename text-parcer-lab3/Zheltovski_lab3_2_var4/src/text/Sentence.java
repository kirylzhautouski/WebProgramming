package text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class representing sentence in the text
 */
public class Sentence extends BaseComplexTextUnit {

    /** Number of words in the sentence, used to set words correctly */
    private int wordCount;

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

        if (unit instanceof Word)
            wordCount++;
    }

    /**
     * Returns all words from the sentence
     * @return words
     */
    public ArrayList<Word> getWords() {
        ArrayList<Word> result = new ArrayList<>();

        for (TextUnit unit : units) {
            if (unit instanceof Word) {
                result.add((Word)unit);
            }
        }

        return result;
    }

    /**
     * Sets new words into the position of all words in the sentence
     * @param words
     */
    public void setWords(ArrayList<Word> words) {
        if (words.size() != wordCount) {
            throw new IllegalArgumentException("Invalid number of words");
        }

        int currentNewWord = 0;
        for (int i = 0; i < units.size(); i++) {
            if (units.get(i) instanceof Word) {
                units.set(i, words.get(currentNewWord));
                currentNewWord++;
            }
        }
    }

    /**
     * Returns number of words in the sentence
     * @return number of words
     */
    public int getWordCount() {
        return wordCount;
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
