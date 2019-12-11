package xml.parsers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import vegetable.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * SAXVegetablesParser used for parsing XML of Vegetables
 */
public class SAXVegetablesParser implements VegetablesXmlParser {


    @Override
    public List<Vegetable> parse(String xmlFileName) throws XmlParsingException {

        try {
            File xmlFile = new File(xmlFileName);

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXHandler saxHandler = new SAXHandler();
            saxParser.parse(xmlFile, saxHandler);

            return saxHandler.getParsedList();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new XmlParsingException();
        }

    }

    private static class SAXHandler extends DefaultHandler {

        private VegetableParseHelper parseHelper = new VegetableParseHelper();

        private List<Vegetable> vegetables = new ArrayList<>();

        public List<Vegetable> getParsedList() {
            return vegetables;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            parseHelper.setCurrentXmlElement(qName);
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            Vegetable vegetable = parseHelper.parseVegetable(qName);
            if (vegetable != null) {
                vegetables.add(vegetable);
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {

            String value = new String(ch, start, length);

            parseHelper.parseContents(value);

        }


    }

}
