import exceptions.KlantException;
import model.Klant;
import model.KlantType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.KlantDao;
import persistence.hsql.HSQLKlantDao;
import services.KlantenService;
import services.KlantenServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KlantenServiceImplTest {

    private KlantenService klantenService;
    private KlantDao klantDao;

    @BeforeEach
    void setUp() {
        // remember Singleton patroon, HSQLKlantDao kan niet zo maar aangemaakt worden, enkel via getInstance()
        KlantDao klantDao = HSQLKlantDao.getInstance("db/testklantendb");
        klantenService = new KlantenServiceImpl(klantDao);
    }

    @Test
    void testGetAllKlanten() {
        // Arrange
        List<Klant> klanten = new ArrayList<>();
        klanten.add(new Klant(1, "Jan", "Janssens", "jan.janssens@example.com",
                KlantType.PARTICULIER, 0.21, LocalDate.now(), false));
        klanten.add(new Klant(2, "Piet", "Pieters", "piet.pieters@example.com",
                KlantType.LEVERANCIER, 0.06, LocalDate.now(), true));
        klanten.forEach(klantDao::insert); // Voeg de klanten toe aan de database

        List<Klant> result = klantenService.getAllKlanten();
        // Controleer of het aantal klanten gelijk is
        assertEquals(klanten.size(), result.size());
    }

    @Test
    void testAddKlant() {
        Klant klant = new Klant(1, "Jan", "Janssens", "jan.janssens@example.com",
                KlantType.PARTICULIER, 0.21, LocalDate.now(), false);
        klantenService.addKlant(klant);
        // Haal de klant op uit de database en controleer of de klant is toegevoegd en check de att's van de klant
        Klant retrievedKlant = klantDao.retrieve("Janssens");
        assertNotNull(retrievedKlant);
        assertEquals(klant.getVoornaam(), retrievedKlant.getVoornaam());
    }
}
