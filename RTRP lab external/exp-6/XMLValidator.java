import java.io.*;
import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;
import org.xml.sax.*;

public class XMLValidator {
    static boolean valErr = false;
    public static void main(String[] args){
        validateWithDTD();
        validateWithXSD("bookstore.xml", "bookstore.xsd");
    }

    private static void validateWithDTD() {
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setValidating(true);
        DocumentBuilder builder = null;

        try {
            builder = domFactory.newDocumentBuilder();
            builder.setErrorHandler(new ErrorHandler() {
                public void fatalError(SAXParseException e) throws SAXException {
                    XMLValidator.valErr = true;
                    System.err.println("Validation with DTD failed. Reason: " + e.getMessage());
               }
                public void error(SAXParseException e) throws SAXException {
                    XMLValidator.valErr = true;
                    System.err.println("Validation with DTD failed. Reason: " + e.getMessage());
                }
                public void warning(SAXParseException e) throws SAXException {
                    XMLValidator.valErr = true;
                    System.err.println("Validation with DTD failed. Reason: " + e.getMessage());
                }
            });
            
            builder.parse("bookstore.xml");

        } catch (SAXException | IOException | ParserConfigurationException e) {
            XMLValidator.valErr = true;
            System.err.println("Validation with DTD failed. Reason: " + e.getMessage());
        }
      
        if (!XMLValidator.valErr) {
            System.out.println("Validation with DTD successful.");
        }
    }

    private static void validateWithXSD(String xmlFile, String xsdFile) {
        try {
            InputStream xmlStream = new FileInputStream(new File(xmlFile));
            InputStream xsdStream = new FileInputStream(new File(xsdFile));
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new StreamSource(xsdStream));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xmlStream));
            System.out.println("Validation with XSD successful.");
        } catch (SAXException | IOException e) {
            System.out.println("Validation with XSD failed. Reason: " + e.getMessage());
        }
    }
}