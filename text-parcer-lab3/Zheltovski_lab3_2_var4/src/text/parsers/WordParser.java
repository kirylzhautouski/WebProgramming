package text.parsers;


import text.Word;

/**
 * Class used for parsing words
 */
public class WordParser implements Parser {

    /**
     * Parses Word from wordString
     * @param wordString
     *        String to be parsed
     * @return Word - word
     */
    @Override
    public Word parse(String wordString) {
        return new Word(wordString);
    }
}
