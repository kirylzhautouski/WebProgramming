package xml.parsers;

import vegetable.Vegetable;

import java.util.List;

public interface VegetablesXmlParser {

    List<Vegetable> parse(String xmlFileName) throws XmlParsingException;

}
