package xml;

import xml.parsers.DOMVegetablesParser;
import xml.parsers.SAXVegetablesParser;
import xml.parsers.STAXVegetablesParser;
import xml.parsers.XmlParsingException;

/**
 * Class containing main method
 */
public class XMLParser {

    public static void main(String[] args) throws XmlParsingException {
        SAXVegetablesParser vegetablesParser = new SAXVegetablesParser();

        System.out.println("SAX: ");
        System.out.println(vegetablesParser.parse("vegetables.xml"));
        System.out.println();

        DOMVegetablesParser domVegetablesParser = new DOMVegetablesParser();

        System.out.println("DOM: ");
        System.out.println(domVegetablesParser.parse("vegetables.xml"));
        System.out.println();

        STAXVegetablesParser staxVegetablesParser = new STAXVegetablesParser();

        System.out.println("STAX: ");
        System.out.println(staxVegetablesParser.parse("vegetables.xml"));
        System.out.println();
    }

}
