package text;

/**
 * Class that represents block of code
 */
public class Code implements TextUnit {

    /** Tag that is used to begin code block */
    public static final String BEGIN_CODE_BLOCK_TAG = "<code>";

    /** Tag that is used to end code block */
    public static final String END_CODE_BLOCK_TAG = "</code>";

    /** String containing code between begin and end code block tags */
    private String codeBlock;

    /**
     * Builds String representation of Code with code block and tags
     * @return String representation of Code
     *
     */
    @Override
    public String build() {
        return BEGIN_CODE_BLOCK_TAG + codeBlock + END_CODE_BLOCK_TAG;
    }

    /**
     * Sets codeBlock
     * @param codeBlock
     *        Code block
     */
    public void setCodeBlock(String codeBlock) {
        this.codeBlock = codeBlock;
    }

    /**
     * Makes code pattern string used for parsing using regular expressions
     * @return Code pattern string
     */
    public static String getCodePatternString() {
        return String.format("%1$s((.|\n)*?)%2$s", Code.BEGIN_CODE_BLOCK_TAG,
            Code.END_CODE_BLOCK_TAG);
    }
}
