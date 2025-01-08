package model;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

/**
 * MULTIKLASSE
 */
public class Klanten implements Serializable {
    // Opdracht 2.1
    @Serial
    private static final long serialVersionUID = 1L;
    private TreeSet<Klant> klanten;

    public Klanten() {
        this.klanten = new TreeSet<>();
    }

    /*Thread safety : synchronized toevoegen  Arraylist of treeset is NIET-THREADSAFE Collection !!
    public synchronized boolean voegToe(Klant klant) {
        return klanten.add(klant);
    }
     */
    public boolean voegToe(Klant klant) {
        return klanten.add(klant);
    }

    public boolean verwijder(String achternaam, String voornaam) {
        for (Klant klant : klanten) {
            if (achternaam.equalsIgnoreCase(klant.getAchternaam()) && voornaam.equals(klant.getVoornaam())) {
                return klanten.remove(klant);
            }
        }
        return false;
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

    //Opdracht 2.3
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Klanten that = (Klanten) o;

        return klanten.equals(that.klanten);
    }

    @Override
    public int hashCode() {
        return klanten.hashCode();
    }
}
