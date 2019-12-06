package text.parsers;

import text.TextUnit;

/**
 * Interfaces for all parsers, that are used to parse String for TextUnit
 */
public interface Parser {

    /**
     * Parses textUnit
     * @param textUnit
     *        String to be parsed
     * @return TextUnit - text unit
     */
    TextUnit parse(String textUnit);

}
