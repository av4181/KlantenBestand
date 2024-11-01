package persist;

import data.Data;
import model.Klant;
import model.Klanten;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

// Opdracht 3.6
class KlantDbDaoTest {
    static KlantDbDao klantDbDao;

    @BeforeAll
    public static void firstOfAll() {
        klantDbDao = new KlantDbDao("db/dbklanten");
    }

    @BeforeEach
    public void init() {
        Data.getData().forEach(f -> klantDbDao.insert(f));
    }

    @AfterEach
    public void teardown() {
        klantDbDao.delete("*");
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
    public void testRetrieveUpdate() {
        Klant retrieved = klantDbDao.retrieve("Janssens");
        retrieved.setAchternaam("Janssens aangepast");
        klantDbDao.update(retrieved);
        Klant afterupdate = klantDbDao.retrieve("Janssens");
        assertNull(afterupdate, "Beide objecten zijn equal na update, check retrieve of update methodes");
    }

    @Test
    void testDelete() {
        int countBeforeDelete = klantDbDao.sortedOn("name").size();
        assertTrue(klantDbDao.delete("Peeters"), "Klant niet correct gedeleted");
        int countAfterDelete = klantDbDao.sortedOn("achternaam").size();
        assertNotEquals(countBeforeDelete, countAfterDelete,
                "Het aantal records in DB mag niet hetzelfde blijven, de delete was niet succesvol");
        assertFalse(klantDbDao.delete("Peeters"),
                "Het zou niet mogelijk mogen zijn om een klant met dezelfde achternaam tweemaal te deleten");

    }

    @AfterAll
    public static void afterAll() {
        klantDbDao.close();
    }

}