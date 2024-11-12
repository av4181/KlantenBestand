package persist;

import data.Data;
import model.Klant;
import model.Klanten;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.*;
import java.util.logging.Level;
import java.util.logging.Logger;

// Opdracht 3.6
class KlantDbDaoTest {
    static KlantDbDao klantDbDao;
    private Connection connection;
    private Logger logger;

    @BeforeAll
    public static void firstOfAll() {
        klantDbDao = new KlantDbDao("jdbc:hsqldb:file:C:/ANDREAS/BIBLIOTHEEK/groeiproject/m6_persistence/db/");
    }

    @BeforeEach
    public void init() {
        Data.getData().forEach(f -> klantDbDao.insert(f));
    }

    @AfterEach
    public void teardown() {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("TRUNCATE TABLE klantentabel");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Fout bij het lezen van de tabel: " + e.getMessage(), e);
        }
    }


    @Test
    public void testInsert() {
        assertEquals(Data.getData().size(), klantDbDao.sortedOn("achternaam").size());
    }

    @Test
    public void testSort() {
        Klanten klanten = new Klanten();
        Data.getData().forEach(klanten::voegToe);
        assertEquals(klantDbDao.sortedOn("aanmaakdatum"), klanten.sorteerOpAanmaakDatum(),
                "De sorteer methode op basis van aanmaakdatum was niet correct geïmplementeerd");
        assertEquals(klantDbDao.sortedOn("type"), klanten.sorteerOpType(),
                "De sorteer methode op basis van klanten type was niet correct geïmplementeerd");
        assertEquals(klantDbDao.sortedOn("btw"), klanten.sorteerOpBtw(),
                "De sorteer methode op basis van btw was niet correct geïmplementeerd");
    }

    @Test
    void testSortedOnAchternaam() {
        List<Klant> klanten = klantDbDao.sortedOnAchternaam();
        assertAll(
                () -> assertEquals(5, klanten.size(), "De lijst zou 5 klanten moeten bevatten."),
                () -> assertEquals("Ankers", klanten.get(0).getAchternaam(), "De eerste klant zou 'Ankers' moeten zijn."),
                () -> assertEquals("Janssens", klanten.get(1).getAchternaam(), "De tweede klant zou 'Janssens' moeten zijn."),
                () -> assertEquals("Janssens", klanten.get(2).getAchternaam(), "De derde klant zou 'Janssens' moeten zijn."),
                () -> assertEquals("Keesen", klanten.get(3).getAchternaam(), "De vierde klant zou 'Keesen' moeten zijn."),
                () -> assertEquals("Pieters", klanten.get(4).getAchternaam(), "De vijfde klant zou 'Pieters' moeten zijn.")
        );
    }

    @Test
    void testSortedOnAanmaakDatum() {
        List<Klant> klanten = klantDbDao.sortedOnAanmaakDatum();
        assertAll(
                () -> assertEquals(5, klanten.size(), "De lijst zou 5 klanten moeten bevatten."),
                () -> assertEquals(LocalDate.of(1980, 3, 5), klanten.get(0).getAanmaakDatum(), "De eerste klant zou aangemaakt moeten zijn op 1980-03-05."),
                () -> assertEquals(LocalDate.of(1985, 12, 20), klanten.get(1).getAanmaakDatum(), "De tweede klant zou aangemaakt moeten zijn op 1985-12-20."),
                () -> assertEquals(LocalDate.of(1990, 5, 10), klanten.get(2).getAanmaakDatum(), "De derde klant zou aangemaakt moeten zijn op 1990-05-10."),
                () -> assertEquals(LocalDate.of(1995, 8, 15), klanten.get(3).getAanmaakDatum(), "De vierde klant zou aangemaakt moeten zijn op 1995-08-15."),
                () -> assertEquals(LocalDate.of(2000, 1, 1), klanten.get(4).getAanmaakDatum(), "De vijfde klant zou aangemaakt moeten zijn op 2000-01-01.")
        );
    }

    @Test
    void testSortedOnBtw() {
        List<Klant> klanten = klantDbDao.sortedOnBtw();
        assertAll(
                () -> assertEquals(5, klanten.size(), "De lijst zou 5 klanten moeten bevatten."),
                () -> assertEquals(0.06, klanten.get(0).getBtw(), 0.001, "De eerste klant zou een BTW van 0.06 moeten hebben."),
                () -> assertEquals(0.06, klanten.get(1).getBtw(), 0.001, "De tweede klant zou een BTW van 0.06 moeten hebben."),
                () -> assertEquals(0.12, klanten.get(2).getBtw(), 0.001, "De derde klant zou een BTW van 0.12 moeten hebben."),
                () -> assertEquals(0.21, klanten.get(3).getBtw(), 0.001, "De vierde klant zou een BTW van 0.21 moeten hebben."),
                () -> assertEquals(0.21, klanten.get(4).getBtw(), 0.001, "De vijfde klant zou een BTW van 0.21 moeten hebben.")
        );
    }

    @Test
    public void testRetrieveUpdate() {
        Klant retrieved = klantDbDao.retrieve("Janssens");
        retrieved.setAchternaam("Janssens aangepast");
        klantDbDao.update(retrieved);
        Klant afterupdate = klantDbDao.retrieve("Janssens");
        assertNull(afterupdate, "Beide objecten zijn equal na update, check retrieve of update methodes");
    }

    @Test
    void testDelete() {
        int countBeforeDelete = klantDbDao.sortedOn("achternaam").size();
        assertTrue(klantDbDao.verwijder("Peeters", "Piet"), "Klant niet correct gedeleted");
        int countAfterDelete = klantDbDao.sortedOn("achternaam").size();
        assertNotEquals(countBeforeDelete, countAfterDelete,
                "Het aantal records in DB mag niet hetzelfde blijven, de delete was niet succesvol");
        assertFalse(klantDbDao.verwijder("Peeters", "Piet"),
                "Het zou niet mogelijk mogen zijn om een klant met dezelfde achternaam en voornaam tweemaal te deleten");
    }

    @AfterAll
    public static void afterAll() {
        klantDbDao.close();
    }

}