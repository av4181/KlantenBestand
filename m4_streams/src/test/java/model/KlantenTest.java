package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KlantenTest {
    //2.2 a)
    private Klant klant1;
    private Klant klant2;
    private Klant klant3;
    private Klant klant4;
    private Klant klant5;
    private Klanten klanten;

    //2.2 b)
    @BeforeEach
    public void setUp() {
        klanten = new Klanten();
        klant1 = new Klant(1L, "Jan", "Janssens", "jan.janssens@example.com",
                KlantType.PARTICULIER, 0.21, LocalDate.of(1990, 5, 10), false);
        klant2 = new Klant(2L, "Piet", "Pieters", "piet.pieters@example.com",
                KlantType.LEVERANCIER, 0.06, LocalDate.of(1985, 12, 20), true);
        klant3 = new Klant(3L, "Anna", "Ankers", "anna.ankers@example.com",
                KlantType.PARTICULIER, 0.12, LocalDate.of(1995, 8, 15), true);
        klant4 = new Klant(4L, "Kees", "Keesen", "kees.keesen@example.com",
                KlantType.LEVERANCIER, 0.21, LocalDate.of(1980, 3, 5), false);
        klant5 = new Klant(5L, "Piet", "Janssens", "piet.janssens@example.com",
                KlantType.PARTICULIER, 0.06, LocalDate.of(2000, 1, 1), true);

        klanten.voegToe(klant1);
        klanten.voegToe(klant2);
        klanten.voegToe(klant3);
        klanten.voegToe(klant4);
        klanten.voegToe(klant5);
    }

    //2.2 c)
    @Test
    public void testToevoegen() {
        assertEquals(5, klanten.getAantal(), "Er zouden 5 klanten moeten zijn.");
        assertFalse(klanten.voegToe(klant1), "Een bestaande klant1 zou niet nogmaals toegevoegd moeten kunnen worden.");
        assertEquals(5, klanten.getAantal(), "Er zouden nog steeds 5 klanten moeten zijn.");
    }

    //2.2 d)
    @Test
    void testVerwijderen() {
        assertTrue(klanten.verwijder("Pieters", "Piet"),
                "Klant2 zou verwijderd moeten worden.");
        assertEquals(4, klanten.getAantal(),
                "Na het verwijderen zouder er nog 4 klanten over moeten zijn.");
        assertFalse(klanten.verwijder("Onbestaande", "Klant"),
                "Een niet-bestaande klant zou niet verwijderd moeten worden.");
        assertEquals(4, klanten.getAantal(),
                "Het aantal klanten zou niet mogen veranderen.");
    }

    //2.2 e)
    @Test
    void testSorterenOpNaam() {
        List<Klant> gesorteerdOpNaam = klanten.sortedBy(Klant::getAchternaam);
        assertAll(
                () -> assertEquals(klant3, gesorteerdOpNaam.get(0),
                        "De eerste klant alfabetisch zou klant3 moeten zijn."),
                () -> assertEquals(klant4, gesorteerdOpNaam.get(1),
                        "De tweede klant alfabetisch zou klant4 moeten zijn."),
                () -> assertEquals(klant5, gesorteerdOpNaam.get(2),
                        "De derde klant alfabetisch zou klant5 moeten zijn.")
        );
    }

    //2.2 f)
    @Test
    void testSorterenOpAanmaakDatum() {
        Klant[] verwachteVolgorde = {klant4, klant2, klant1, klant3, klant5};
        List<Klant> gesorteerdOpAanmaakDatum = klanten.sortedBy(Klant::getAanmaakDatum);
        assertArrayEquals(verwachteVolgorde, gesorteerdOpAanmaakDatum.toArray(),
                "De klanten zouden gesorteerd moeten zijn op aanmaakdatum.");
    }

}