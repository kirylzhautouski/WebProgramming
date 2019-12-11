package xml.parsers;

import vegetable.*;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Parse XML of Vegetables using STaX analyzer
 */
public class STAXVegetablesParser implements VegetablesXmlParser {

    private VegetableParseHelper parseHelper = new VegetableParseHelper();

    @Override
    public List<Vegetable> parse(String xmlFileName) throws XmlParsingException {

        List<Vegetable> vegetables = new ArrayList<>();

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader eventReader;
        try {
            eventReader = factory.createXMLEventReader(new FileReader(xmlFileName));
        } catch (XMLStreamException | FileNotFoundException e) {
            throw new XmlParsingException();
        }

        while (eventReader.hasNext()) {

            XMLEvent event;

            try {
                event = eventReader.nextEvent();
            } catch (XMLStreamException e) {
                throw new XmlParsingException();
            }

            switch (event.getEventType()) {
                case XMLStreamConstants.START_ELEMENT:
                    StartElement startElement = event.asStartElement();
                    parseHelper.setCurrentXmlElement(startElement.getName().getLocalPart());

                    break;

                case XMLStreamConstants.CHARACTERS:
                    String value = event.asCharacters().getData();
                    parseHelper.parseContents(value);

                    break;
                case XMLStreamConstants.END_ELEMENT:
                    EndElement endElement = event.asEndElement();
                    Vegetable vegetable = parseHelper.parseVegetable(endElement.getName().getLocalPart());

                    if (vegetable != null) {
                        vegetables.add(vegetable);
                    }

                    break;
            }

        }


        return vegetables;
    }

}
