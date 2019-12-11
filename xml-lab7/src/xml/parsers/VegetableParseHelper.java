package xml.parsers;

import vegetable.*;

/**
 * Helper class used for parsing Vegetables from XML
 */
public class VegetableParseHelper {

    protected double weightInGramms;
    protected VegetableState vegetableState;
    protected TomatoColor tomatoColor;
    protected boolean isGeneticallyModified;
    protected boolean isGrandmas;

    protected String currentXmlElement;
    protected boolean hasContentToParse;

    private static final String[] NESTED_ELEMENTS = new String[] { "weightInGramms", "vegetableState", "tomatoColor",
        "isGeneticallyModified", "isGrandmas" };

    public void setCurrentXmlElement(String currentXmlElement) {
        this.currentXmlElement = currentXmlElement;

        if (isNestedXMLElement(currentXmlElement)) {
            hasContentToParse = true;
        }
    }

    public void parseContents(String value) {
        if (hasContentToParse) {
            switch (currentXmlElement) {
                case "weightInGramms":
                    weightInGramms = Double.parseDouble(value);

                    break;
                case "vegetableState":
                    vegetableState = VegetableState.valueOf(value.toUpperCase());

                    break;
                case "tomatoColor":
                    tomatoColor = TomatoColor.valueOf(value.toUpperCase());

                    break;
                case "isGeneticallyModified":
                    isGeneticallyModified = Boolean.parseBoolean(value);

                    break;
                case "isGrandmas":
                    isGrandmas = Boolean.parseBoolean(value);

                    break;
            }

            hasContentToParse = false;
        }
    }

    public Vegetable parseVegetable(String currentXmlElement) {
        switch (currentXmlElement) {
            case "tomato":
                return new Tomato(weightInGramms, vegetableState, tomatoColor);

            case "carrot":
                return new Carrot(weightInGramms, vegetableState, isGeneticallyModified);

            case "cucumber":
                return new Cucumber(weightInGramms, vegetableState, isGrandmas);

            default:
                return null;
        }
    }

    protected static boolean isNestedXMLElement(String elementName) {
        for (String nestedElement : NESTED_ELEMENTS) {
            if (nestedElement.equals(elementName)) {
                return true;
            }
        }

        return false;
    }

}
