package text;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class representing text
 */
public class Text extends BaseComplexTextUnit {

    /**
     * Builds String representation of Text. It concatenates paragraphs,
     * code blocks and sentences
     * @return String representation of Text
     */
    @Override
    public String build() {
        StringBuilder textBuilder = new StringBuilder(units.get(0).build());

        for (int i = 1; i < units.size(); i++) {
            if (!(units.get(i - 1) instanceof Paragraph))
                textBuilder.append(" ");

            textBuilder.append(units.get(i).build());
        }

        return textBuilder.toString();
    }

    /**
     * Add unit to Text. Text amy contain Sentence, Code and Paragraph
     * @param unit
     *        Unit to be added
     */
    @Override
    public void addUnit(TextUnit unit) {
        if (!(unit instanceof Sentence || unit instanceof Code || unit instanceof Paragraph))
            throw new IllegalArgumentException("Text may contain only Sentence and Code");

        super.addUnit(unit);
    }

    /**
     * Returns all words in the text
     * @return words
     */
    public ArrayList<Word> getWords() {
        ArrayList<Word> result = new ArrayList<>();

        for (TextUnit unit : units) {
            if (unit instanceof Sentence) {
                result.addAll(((Sentence) unit).getWords());
            }
        }

        return result;
    }

    /**
     * Returns an array of all words from the text sorted by vowels share
     * @return words
     */
    public List<Word> sortWordsByVowelsShare() {
        ArrayList<Word> result = getWords();

        result.sort(Comparator.comparingDouble(Word::getVowelsShare));

        return result;
    }

    /**
     * Returns an array of words with the first vowel sorted by first consonant
     * @return words
     */
    public List<Word> sortWordsWithFirstVowelByFirstConsonant() {
        ArrayList<Word> allWords = getWords();

        String firstVowel = String.format("^[%s].*", Word.VOWELS);

        return allWords.stream()
            .filter(s -> s.build().toLowerCase().matches(firstVowel))
            .sorted((a, b) -> {
                Character firstConsonantA = a.getFirstConsonant();
                Character firstConsonantB = b.getFirstConsonant();

                if (firstConsonantA == null) {
                    if (firstConsonantB == null) {
                        return 0;
                    }

                    return 1;
                } else if (firstConsonantB == null) {
                    return -1;
                }

                return firstConsonantA.compareTo(firstConsonantB);
            }).collect(Collectors.toList());
    }
}
