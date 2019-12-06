package localization;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleManager {

    private LocaleManager() {

    }

    public static String DEFAULT_LOCALE = "ru";
    private static Locale locale = new Locale(DEFAULT_LOCALE);

    private static final String BUNDLE_PATH = "localization/locales/locale";
    private static ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_PATH, locale);

    public static String INPUT_TEXT = "inputText";
    public static String OUTPUT_TEXT = "outputText";

    public static String VOWELS = "vowels";

    public static String WORDS_SORTED_BY_VOWELS_SHARE = "wordsSortedByVowelsShare";
    public static String WORDS_WITH_FIRST_VOWEL_SORTED_BY_FIRST_CONSONANT =
        "wordsWithFirstVowelSortedByFirstConsonant";

    public static String FILE_READ_ERROR = "fileReadError";

    public static String ILLEGAL_PUNCTUATION_MARK = "illegalPunctuationMark";

    public static String INVALID_FILE = "invalidFile";

    public static String INVALID_PUNCTUATION_MARK = "invalidPunctuationMark";

    public static String NO_WORDS = "noWords";

    /**
     * Returns default locale
     * @return default locale
     */
    public static Locale getDefaultLocale() {
        return new Locale(DEFAULT_LOCALE);
    }

    /**
     * Returns current locale
     * @return current locale
     */
    public static Locale getCurrentLocale() {
        return locale;
    }

    /**
     * Sets locale
     * @param localeStr locale
     */
    public static void setLocale(String localeStr) {
        locale = new Locale(localeStr);
        bundle = ResourceBundle.getBundle(BUNDLE_PATH, locale);
    }

    /**
     * Returns localized string from the proper bundle according to the locale
     * @param key key
     * @return localized string
     */
    public static String getLocalizedString(String key) {
        return bundle.getString(key);
    }
}
