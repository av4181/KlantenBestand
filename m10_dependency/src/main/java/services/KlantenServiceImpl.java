package services;

import model.Klant;
import persistence.KlantDao;
import java.util.List;

public class KlantenServiceImpl implements KlantenService {

    // 2.4 c) dependecy injection, werken op de interface niet de Dao klasse zelf
    private final KlantDao klantDao;

    public KlantenServiceImpl(KlantDao klantDao) { // Constructor injection
        this.klantDao = klantDao;
    }

    // 2.4 b)
    @Override
    public List<Klant> getAllKlanten() {
        return klantDao.getAllKlanten();
    }

    // 2.4 c)
    @Override
    public void addKlant(Klant klant) {
        klantDao.insert(klant);
    }
}
