package persist;

import data.Data;
import model.Klanten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Opdracht 2.3
class KlantSerializerTest {
    private static Klanten klanten;
    KlantSerializer klantSerializer;


    @BeforeEach
    public void init(){
        klantSerializer = new KlantSerializer("db/klantentabel.ser");
        klanten = new Klanten();
        Data.getData().forEach(k -> klanten.voegToe(k));

    }

    @Test
    void testSerialize() {
        assertDoesNotThrow(() -> klantSerializer.serialize(klanten),
                "The serialization did not work and threw an exception");
    }

    @Test
    void testDeserialize() {
        assertDoesNotThrow(() ->assertEquals(klanten, klantSerializer.deserialize(),
                "Deserialization did not work"), "Deserialization threw an exception");
    }

}