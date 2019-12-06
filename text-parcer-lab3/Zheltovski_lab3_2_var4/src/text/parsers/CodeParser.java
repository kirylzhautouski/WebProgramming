package text.parsers;

import text.Code;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class used for parsing code blocks
 */
public class CodeParser implements Parser {

    /**
     * Parses code block from codeString
     * @param codeString
     *        String to parse
     * @return Code - code block
     */
    @Override
    public Code parse(String codeString) {
        Code result = new Code();

        Pattern pattern = Pattern.compile(Code.getCodePatternString());
        Matcher matcher = pattern.matcher(codeString);

        String codeBlock = null;

        while (matcher.find()) {
            codeBlock = matcher.group(1);
        }

        result.setCodeBlock(codeBlock);

        return result;
    }
}
