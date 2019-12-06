package text.parsers;

import text.Sentence;

/**
 * Class that is used for parsing sentences
 */
public class SentenceParser implements Parser {

    /** Word parser */
    private WordParser wordParser = new WordParser();

    /** Punctuation mark parser */
    private PunctuationMarkParser punctuationMarkParser = new PunctuationMarkParser();

    /**
     * Parses Sentence from sentenceString
     * @param sentenceString
     *        String to be parsed
     * @return Sentence - sentence
     */
    @Override
    public Sentence parse(String sentenceString) {
        Sentence result = new Sentence();

        String[] splitResult = sentenceString.split("\\b");

        for (String unit : splitResult) {
            unit = unit.trim();

            if (unit.length() > 1) {
                result.addUnit(wordParser.parse(unit));
            } else if (unit.length() == 1) {
                if (!unit.equals(" "))
                    result.addUnit(punctuationMarkParser.parse(unit));
            }
        }

        return result;
    }


}
