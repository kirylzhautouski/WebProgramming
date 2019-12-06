package text;

import localization.LocaleManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class representing words in the text
 */
public class Word implements TextUnit {

    public static final String VOWELS = LocaleManager.VOWELS;

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
     * Counts and returns vowels share
     * @return vowels share from 0.0 to 1.0
     */
    public double getVowelsShare() {
        int vowelCount = 0;

        String vowels = String.format("[%s]", Word.VOWELS);

        Pattern vowelsPattern = Pattern.compile(vowels);

        Matcher matcher = vowelsPattern.matcher(word.toLowerCase());
        while (matcher.find()) {
            vowelCount++;
        }

        return (double)vowelCount / word.length();
    }

    /**
     * Finds and returns first consonant
     * @return first consonant
     */
    public Character getFirstConsonant() {
        String firstConsonant = String.format("[^%s]", Word.VOWELS);

        Pattern firstConsonantPattern = Pattern.compile(firstConsonant);

        Matcher matcher = firstConsonantPattern.matcher(word.toLowerCase());
        if (matcher.find()) {
            return matcher.group().charAt(0);
        }

        return null;
    }

    /**
     * Builds String representation of Word
     * @return String representation of Word
     */
    @Override
    public String build() {
        return word;
    }

    @Override
    public String toString() {
        return word;
    }
}
