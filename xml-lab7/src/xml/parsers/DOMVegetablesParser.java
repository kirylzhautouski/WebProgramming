package xml.parsers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import vegetable.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DOMVegetablesParser implements VegetablesXmlParser {

    @Override
    public List<Vegetable> parse(String xmlFileName) throws XmlParsingException {

        List<Vegetable> vegetables = new ArrayList<>();

        File xmlFile = new File(xmlFileName);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        Document document;

        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(xmlFile);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            throw new XmlParsingException();
        }

        document.getDocumentElement().normalize();

        NodeList tomatoNodes = document.getElementsByTagName("tomato");
        NodeList carrotNodes = document.getElementsByTagName("carrot");
        NodeList cucumberNodes = document.getElementsByTagName("cucumber");

        for (int i = 0; i < tomatoNodes.getLength(); i++) {
            Node node = tomatoNodes.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element)node;

                double weightInGramms = Double.parseDouble(element.getElementsByTagName("weightInGramms").item(0)
                    .getTextContent());

                VegetableState vegetableState = VegetableState.valueOf(element.getElementsByTagName("vegetableState").item(0)
                    .getTextContent().toUpperCase());

                TomatoColor tomatoColor = TomatoColor.valueOf(element.getElementsByTagName("tomatoColor").item(0)
                    .getTextContent().toUpperCase());

                vegetables.add(new Tomato(weightInGramms, vegetableState, tomatoColor));

            }

        }

        for (int i = 0; i < carrotNodes.getLength(); i++) {
            Node node = carrotNodes.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element)node;

                double weightInGramms = Double.parseDouble(element.getElementsByTagName("weightInGramms").item(0)
                    .getTextContent());

                VegetableState vegetableState = VegetableState.valueOf(element.getElementsByTagName("vegetableState").item(0)
                    .getTextContent().toUpperCase());

                boolean isGeneticallyModified = Boolean.parseBoolean(element.getElementsByTagName("isGeneticallyModified")
                    .item(0).getTextContent());

                vegetables.add(new Carrot(weightInGramms, vegetableState, isGeneticallyModified));

            }

        }

        for (int i = 0; i < cucumberNodes.getLength(); i++) {
            Node node = cucumberNodes.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element)node;

                double weightInGramms = Double.parseDouble(element.getElementsByTagName("weightInGramms").item(0)
                    .getTextContent());

                VegetableState vegetableState = VegetableState.valueOf(element.getElementsByTagName("vegetableState").item(0)
                    .getTextContent().toUpperCase());

                boolean isGrandmas = Boolean.parseBoolean(element.getElementsByTagName("isGrandmas")
                    .item(0).getTextContent());

                vegetables.add(new Cucumber(weightInGramms, vegetableState, isGrandmas));

            }

        }

        return vegetables;

    }
}
