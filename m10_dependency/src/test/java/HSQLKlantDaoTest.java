import data.Data;
import model.Klant;
import model.KlantType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.hsql.HSQLKlantDao;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HSQLKlantDaoTest {

    private HSQLKlantDao klantDao;

    @BeforeEach
    void setUp() {
        klantDao = HSQLKlantDao.getInstance();
    }

    @AfterEach
    void tearDown() {
        klantDao.verwijder("*", "*");
        klantDao.close();
    }

    @Test
    void testInsert() {
        Klant klant = new Klant(1, "Jan", "Janssens", "jan.janssens@example.com",
                KlantType.PARTICULIER, 0.21, LocalDate.now(), false);
        assertTrue(klantDao.insert(klant));
        Klant opgehaaldeKlant = klantDao.retrieve("Janssens");
        assertEquals(klant, opgehaaldeKlant);
    }

    @Test
    void testVerwijder() {
        Klant klant = new Klant(1, "Jan", "Janssens", "jan.janssens@example.com",
                KlantType.PARTICULIER, 0.21, LocalDate.now(), false);
        klantDao.insert(klant);
        assertTrue(klantDao.verwijder("Janssens", "Jan"));
        assertNull(klantDao.retrieve("Janssens"));
    }

    @Test
    void testUpdate() {
        Klant klant = new Klant(1, "Jan", "Janssens", "jan.janssens@example.com",
                KlantType.PARTICULIER, 0.21, LocalDate.now(), false);
        klantDao.insert(klant);
        klant.setVoornaam("Piet");
        assertTrue(klantDao.update(klant));
        Klant opgehaaldeKlant = klantDao.retrieve("Janssens");
        assertEquals("Piet", opgehaaldeKlant.getVoornaam());
    }

    @Test
    void testRetrieve() {
        Klant klant = new Klant(1, "Jan", "Janssens", "jan.janssens@example.com",
                KlantType.PARTICULIER, 0.21, LocalDate.now(), false);
        klantDao.insert(klant);
        Klant opgehaaldeKlant = klantDao.retrieve("Janssens");
        assertEquals(klant, opgehaaldeKlant);
    }

    @Test
    void testSortedOn() {
        List<Klant> klanten = Data.getData();
        klanten.forEach(klantDao::insert);
        List<Klant> gesorteerdeKlanten = klantDao.sortedOn("achternaam");
    }

    @Test
    void testGetAllKlanten() {
        List<Klant> klanten = Data.getData();
        klanten.forEach(klantDao::insert);
        List<Klant> alleKlanten = klantDao.getAllKlanten();
        assertEquals(klanten.size(), alleKlanten.size());
    }
}
