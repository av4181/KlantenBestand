package model;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * MULTIKLASSE
 */
public class Klanten {
    private TreeSet<Klant> klanten;
    //3.3 Logging op de multiklasse
    private static final Logger logger = Logger.getLogger(Klanten.class.getName());

    public Klanten() {
        this.klanten = new TreeSet<>();
    }

    //3.3 Finer logging
    public boolean voegToe(Klant klant) {
        if (klanten.add(klant)) {
            logger.log(Level.FINER, "Klant {0} {1} is toegevoegd aan de lijst", new Object[]{klant.getVoornaam(),
                    klant.getAchternaam()});
            return true;
        } else {
            return false;
        }
    }

    //3.3 Finer logging
    public boolean verwijder(String achternaam, String voornaam) {
        Klant klant = zoek(achternaam, voornaam);
        if (klant != null && klanten.remove(klant)) {
            logger.log(Level.FINER, "Klant {0} {1} is verwijderd uit de lijst", new Object[]{klant.getVoornaam(),
                    klant.getAchternaam()});
            return true;
        } else {
            return false;
        }
    }

    public Klant zoek(String naam, String voornaam) {
        for (Klant klant : klanten) {
            if (naam.equalsIgnoreCase(klant.getAchternaam()) && voornaam.equals(klant.getVoornaam())) {
                return klant;
            }
        }
        return null;
    }

    public List<Klant> sorteerOpType() {
        List<Klant> klantType = new ArrayList<>(klanten);
        klantType.sort(new Comparator<Klant>() {
            @Override
            public int compare(Klant o1, Klant o2) {
                return o1.getType().compareTo(o2.getType());
            }
        });
        return Collections.unmodifiableList(klantType);
    }

    public List<Klant> sorteerOpBtw() {
        List<Klant> klantBtw = new ArrayList<>(klanten);
        klantBtw.sort(new Comparator<Klant>() {
            @Override
            public int compare(Klant o1, Klant o2) {
                return Double.compare(o1.getBtw(), (o2.getBtw()));
            }
        });
        return Collections.unmodifiableList(klantBtw);
    }

    public List<Klant> sorteerOpAanmaakDatum() {
        List<Klant> aanmaakDatum = new ArrayList<>(klanten);
        aanmaakDatum.sort(new Comparator<Klant>() {
            @Override
            public int compare(Klant o1, Klant o2) {
                return o1.getAanmaakDatum().compareTo(o2.getAanmaakDatum());
            }
        });
        return Collections.unmodifiableList(aanmaakDatum);
    }

    public int getAantal() {
        return klanten.size();
    }
}
