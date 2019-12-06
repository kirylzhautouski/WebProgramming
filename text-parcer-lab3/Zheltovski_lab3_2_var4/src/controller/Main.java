package controller;

import localization.LocaleManager;
import text.Text;
import text.parsers.TextParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * Class containing main method - an entry point of the application
 */
public class Main {

    /** Input text containing sentences, paragraphs and code blocks */
    private static final String TEXT_STRING = "<code>cout << \"Hello World\";</code>\nThis regular, expression,is useful!\nIt will split: up the input ; into ;separate sentences. <code>World</code> How does it work?";

    /**
     * Loads all contents from file
     * @param filePath file path
     * @return text string
     */
    private static String loadTextStringFromFile(String filePath) {
        String textString = null;

        try {
            textString = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            System.out.println(LocaleManager.getLocalizedString(LocaleManager.FILE_READ_ERROR));
        }

        return textString;
    }

    /**
     * main method - an entry point of the application
     * @param args
     *        Command-line arguments
     */
    public static void main(String[] args) {
        TextParser parser = new TextParser();

        String textString = loadTextStringFromFile("input.txt");

        if (textString != null) {
            Text text = parser.parse(textString);

            System.out.println(LocaleManager.getLocalizedString(LocaleManager.INPUT_TEXT));
            System.out.println(textString);

            System.out.println();

            System.out.println(LocaleManager.getLocalizedString(LocaleManager.OUTPUT_TEXT));
            System.out.println(text.build());

            System.out.println();

            System.out.println(LocaleManager.getLocalizedString(LocaleManager.WORDS_SORTED_BY_VOWELS_SHARE));
            System.out.println(text.sortWordsByVowelsShare());

            System.out.println();

            System.out.println(LocaleManager.getLocalizedString(LocaleManager.WORDS_WITH_FIRST_VOWEL_SORTED_BY_FIRST_CONSONANT));
            System.out.println(text.sortWordsWithFirstVowelByFirstConsonant());
        }
    }

}
