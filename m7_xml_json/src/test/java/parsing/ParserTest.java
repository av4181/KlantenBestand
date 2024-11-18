package parsing;

import data.Data;
import model.Klant;
import model.Klanten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    String pathStax;
    String pathGson;
    String pathJaxb;
    private static Klanten klanten;


    @BeforeEach
    public void init(){
        klanten = new Klanten();
        pathStax = "datafiles/klantStax.xml";
        pathJaxb = "datafiles/klantJaxb.xml";
        pathGson = "datafiles/klantGson.json";
        Data.getData().forEach(f ->klanten.voegToe(f));
    }

    // Opdracht 2.5
    @Test
    void testStaxDom() {
        KlantenStaxParser parser  = assertDoesNotThrow(() -> new KlantenStaxParser(klanten, pathStax), "issue with parser");
        assertDoesNotThrow(parser::staxWriteXML, "issue with writing to xml");

        Klanten klantenNaParsing = assertDoesNotThrow(() -> KlantenDomParser.domReadXML(pathStax), "issue with domreadXml");
        assertEquals(klanten.sorteerOpType(), klantenNaParsing.sorteerOpType(), "object are not equal");
    }

    // Opdracht 2.8
    @Test
    void testJaxb(){
        assertDoesNotThrow(() -> KlantenJaxbParser.JaxbWriteXml(pathJaxb, klanten), "issue with Jaxb parser");
        Klanten klanten1 = assertDoesNotThrow(() -> KlantenJaxbParser.JaxbReadXml(pathJaxb, Klanten.class), "issue with writing to xml");

        assertEquals(klanten.sorteerOpType(), klanten1.sorteerOpType(), "object are not equal");

    }

    @Test
    void testGson(){
        assertDoesNotThrow(() -> KlantenGsonParser.writeJson(klanten, pathGson), "Issue writing XML using json");
        Klanten footballStadiumsFromGson =  assertDoesNotThrow(() ->KlantenGsonParser.readJson(pathGson), "Issue");

        assertEquals( klanten.getKlantenLijst(), footballStadiumsFromGson.getKlantenLijst(), "Not the same");
    }
}
