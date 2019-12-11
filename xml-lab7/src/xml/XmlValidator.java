package xml;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;

public class XmlValidator {

    public static void main(String[] args) {
        System.out.println(validateXmlBySchema("vegetables.xml", "vegetables_schema.xsd"));
    }

    private static boolean validateXmlBySchema(String xmlFileName, String xsdFileName) {

        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File(xsdFileName));

            Validator validator = schema.newValidator();

            validator.validate(new StreamSource(new File(xmlFileName)));
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return false;
        }

        return true;
    }

}
