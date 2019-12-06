package controller;

import exceptions.FileException;
import exceptions.NoWordsException;
import localization.LocaleManager;
import text.Text;
import text.parsers.TextParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import org.apache.log4j.*;


/**
 * Class containing main method - an entry point of the application
 */
public class Main {

    private static final Logger LOGGER = Logger.getLogger("main");

    /** Input text containing sentences, paragraphs and code blocks */
    private static final String TEXT_STRING = "<code>cout << \"Hello World\";</code>\nThis regular, expression,is useful!\nIt will split: up the input ; into ;separate sentences. <code>World</code> How does it work?";

    /**
     * Loads all contents from file
     * @param filePath file path
     * @return text string
     */
    private static String loadTextStringFromFile(String filePath) throws FileException {
        LOGGER.info("Loading text from " + filePath);

        String textString = null;

        try {
            textString = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            LOGGER.warn("Error reading from file");
            throw new FileException(LocaleManager.getLocalizedString(LocaleManager.FILE_READ_ERROR));
        }

        LOGGER.info("Finish loading text from " + filePath);

        return textString;
    }

    /**
     * main method - an entry point of the application
     * @param args
     *        Command-line arguments
     */
    public static void main(String[] args) {
        LOGGER.info("Starting program");

        TextParser parser = new TextParser();

        try {
            String textString = loadTextStringFromFile("input.txt");

            if (textString != null) {

                LOGGER.info("Parsing the text");

                Text text = parser.parse(textString);

                LOGGER.info("Ended parsing the text");

                System.out.println(LocaleManager.getLocalizedString(LocaleManager.INPUT_TEXT));
                System.out.println(textString);

                System.out.println();

                System.out.println(LocaleManager.getLocalizedString(LocaleManager.OUTPUT_TEXT));

                LOGGER.info("Building the text");
                System.out.println(text.build());
                LOGGER.info("Ended building the text");

                System.out.println();

                LOGGER.info("Sorting words by vowels share");
                System.out.println(LocaleManager.getLocalizedString(LocaleManager.WORDS_SORTED_BY_VOWELS_SHARE));
                System.out.println(text.sortWordsByVowelsShare());
                LOGGER.info("Ended sorting words by vowels share");

                System.out.println();

                LOGGER.info("Sorting words with first vowel by first consonant");
                System.out.println(LocaleManager.getLocalizedString(LocaleManager.WORDS_WITH_FIRST_VOWEL_SORTED_BY_FIRST_CONSONANT));
                System.out.println(text.sortWordsWithFirstVowelByFirstConsonant());
                LOGGER.info("Ended sorting words with first vowel by first consonant");
            }
        } catch (FileException | NoWordsException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();

            LOGGER.warn(Arrays.toString(ex.getStackTrace()));
        }

        LOGGER.info("Ending program");
    }

}
