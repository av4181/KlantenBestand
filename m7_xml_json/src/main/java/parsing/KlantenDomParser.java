package parsing;

import model.Klant;
import model.Klanten;
import model.KlantType;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class KlantenDomParser {

    public static Klanten domReadXML(String fileName) {
        Klanten klanten = new Klanten();
        try {
            File xmlFile = new File(fileName);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);

            NodeList klantNodes = document.getElementsByTagName("klant");
            for (int i = 0; i < klantNodes.getLength(); i++) {
                Node klantNode = klantNodes.item(i);
                if (klantNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element klantElement = (Element) klantNode;
                    Klant klant = createKlantFromElement(klantElement);
                    klanten.voegToe(klant);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return klanten;
    }

    private static Klant createKlantFromElement(Element klantElement) {
        int id = Integer.parseInt(klantElement.getElementsByTagName("id").item(0).getTextContent());
        String voornaam = klantElement.getElementsByTagName("voornaam").item(0).getTextContent();
        String achternaam = klantElement.getElementsByTagName("achternaam").item(0).getTextContent();
        String email = klantElement.getElementsByTagName("email").item(0).getTextContent();
        KlantType type = KlantType.valueOf(klantElement.getAttribute("type"));
        double btw = Double.parseDouble(klantElement.getElementsByTagName("btw").item(0).getTextContent());
        LocalDate aanmaakDatum = LocalDate.parse(klantElement.getElementsByTagName("aanmaakDatum").item(0).getTextContent());
        boolean redflag = Boolean.parseBoolean(klantElement.getElementsByTagName("redflag").item(0).getTextContent());

        return new Klant(id, voornaam, achternaam, email, type, btw, aanmaakDatum, redflag);
    }
}
