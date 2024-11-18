package parsing;

import com.sun.xml.txw2.output.IndentingXMLStreamWriter;
import model.Klant;
import model.Klanten;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

//Opdracht 2.2 - schrijven naar een file gebruik makend van xmlstream
public class KlantenStaxParser {
    private Klanten klanten;
    private XMLStreamWriter xmlStreamWriter;

    public KlantenStaxParser(Klanten klanten, String path) {
        this.klanten = klanten;
        setupXML(path);
    }


    private void setupXML(String path) {
        try {
            FileWriter fileWriter =new FileWriter(path, StandardCharsets.UTF_8);
            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
            xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(new FileWriter(path));
             // Mooie layout met IndentingXMLStreamWriter
            this.xmlStreamWriter = new IndentingXMLStreamWriter(this.xmlStreamWriter);
        } catch (IOException | XMLStreamException e) {
            e.printStackTrace();
        }
    }

    //Opdarcht 2.3 multiobject wegschrijven via STAX
    public void staxWriteXML() throws XMLStreamException {
        xmlStreamWriter.writeStartDocument();
        xmlStreamWriter.writeStartElement("klanten");

        for (Klant klant : klanten.getKlantenLijst()) {
            writeElement(klant);
        }

        xmlStreamWriter.writeEndElement();
        xmlStreamWriter.writeEndDocument();
        xmlStreamWriter.close();
    }

    private void writeElement(Klant klant) throws XMLStreamException {
        xmlStreamWriter.writeStartElement("klant");
        xmlStreamWriter.writeAttribute("type", klant.getType().toString());

        xmlStreamWriter.writeStartElement("id");
        xmlStreamWriter.writeCharacters(String.valueOf(klant.getId()));
        xmlStreamWriter.writeEndElement();

        xmlStreamWriter.writeStartElement("voornaam");
        xmlStreamWriter.writeCharacters(klant.getVoornaam());
        xmlStreamWriter.writeEndElement();

        xmlStreamWriter.writeStartElement("achternaam");
        xmlStreamWriter.writeCharacters(klant.getAchternaam());
        xmlStreamWriter.writeEndElement();

        xmlStreamWriter.writeStartElement("email");
        xmlStreamWriter.writeCharacters(klant.getEmail());
        xmlStreamWriter.writeEndElement();

        xmlStreamWriter.writeStartElement("btw");
        xmlStreamWriter.writeCharacters(String.valueOf(klant.getBtw()));
        xmlStreamWriter.writeEndElement();

        xmlStreamWriter.writeStartElement("aanmaakDatum");
        xmlStreamWriter.writeCharacters(klant.getAanmaakDatum().toString());
        xmlStreamWriter.writeEndElement();

        xmlStreamWriter.writeStartElement("redflag");
        xmlStreamWriter.writeCharacters(String.valueOf(klant.getRedflag()));
        xmlStreamWriter.writeEndElement();

        xmlStreamWriter.writeEndElement();
    }

}
