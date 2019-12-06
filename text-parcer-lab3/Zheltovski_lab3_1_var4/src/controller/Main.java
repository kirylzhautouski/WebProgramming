package controller;

import text.Text;
import text.parsers.TextParser;

/**
 * Class containing main method - an entry point of the application
 */
public class Main {

    /** Input text containing sentences, paragraphs and code blocks */
    private static final String TEXT_STRING = "<code>cout << \"Hello World\";</code>\nThis regular, expression,is useful!\nIt will split: up the input ; into ;separate sentences. <code>World</code> How does it work?";

    /**
     * main method - an entry point of the application
     * @param args
     *        Command-line arguments
     */
    public static void main(String[] args) {
        TextParser parser = new TextParser();

        Text text = parser.parse(TEXT_STRING);

        System.out.println("Input:");
        System.out.println(TEXT_STRING);

        System.out.println();

        System.out.println("Output");
        System.out.println(text.build());
    }

}
