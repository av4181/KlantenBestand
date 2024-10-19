package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class KlantTest {
    private Klant klant1;
    private Klant klant2;

    @BeforeEach
    public void setUp() {
        klant1 = new Klant(1L, "Jan", "Janssens", "jan.janssens@example.com",
                KlantType.PARTICULIER, 0.21, LocalDate.of(1990, 5, 10), false);
        klant2 = new Klant(2L, "Piet", "Pieters", "piet.pieters@example.com",
                KlantType.LEVERANCIER, 0.06, LocalDate.of(1985, 12, 20), true);
    }

    //2.1 e)
    @Test
    public void testEquals() {
        assertNotEquals(klant1, klant2, "De klanten zouden verschillend moeten zijn.");
        Klant klant3 = new Klant(1L, "Jan", "Janssens", "jan.janssens@example.com",
                KlantType.PARTICULIER, 0.21, LocalDate.of(1990, 5, 10), false);
        assertEquals(klant1, klant3, "De klanten zouden dezelfde moeten zijn.");
    }

    //2.1 f)
    @Test
    public void testIllegalVoornaam() {
        assertThrows(IllegalArgumentException.class, () -> klant1.setVoornaam(""),
                "Voornaam mag niet leeg zijn, moet een IllegalArgumentException geven.");
    }

    //2.1 g)
    @Test
    public void testLegalVoornaam() {
        assertDoesNotThrow(() -> klant1.setVoornaam("Johan"),
                "Een ingevuld voornaam mag geen IllegalArgumentException geven.");
    }

    //2.1 h)
    @Test
    public void testCompareTo() {
        assertTrue(klant1.compareTo(klant2) < 0,
                "Klant1 zou alfabetisch als eerste in de lijst moeten staan");
        Klant klant3 = new Klant(3L, "Jan", "Janssens", "jan.janssens@example.com",
                KlantType.PARTICULIER, 0.21, LocalDate.of(1990, 5, 10), false);
        assertEquals(0, klant1.compareTo(klant3),
                "Klant1 en klant3 zouden alfabetisch gelijk moeten zijn op basis van voor -en achternaam.");
    }

    //2.1 i)
    @Test
    public void testBtw() {
        assertEquals(0.21, klant1.getBtw(), 0.001,
                "BTW attribuut waarde van klant1 zou 0.21 moeten zijn.");
    }

}