package model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.*;

/**
 * MULTIKLASSE
 */

//Opdracht 2.6 JAXB
@XmlRootElement(name="klanten")
public class Klanten {
    private List<Klant> klantenLijst = new ArrayList<>();

    public Klanten() {
        this.klantenLijst = new ArrayList<>();
    }

    public boolean voegToe(Klant klant) {
        return klantenLijst.add(klant);
    }

    public boolean verwijder(String achternaam, String voornaam) {
        for (Klant klant : klantenLijst) {
            if (achternaam.equalsIgnoreCase(klant.getAchternaam()) && voornaam.equals(klant.getVoornaam())) {
                return klantenLijst.remove(klant);
            }
        }
        return false;
    }

    // Deel 7: methode voor xml
    public List<Klant> getKlantenLijst() {
        return klantenLijst;
    }

    // Opdracht 2.6
    @XmlElement(name = "klant")
    public void setKlantenLijst(List<Klant> klantenLijst) {
        this.klantenLijst = klantenLijst;
    }

    public Klant zoek(String naam, String voornaam) {
        for (Klant klant : klantenLijst) {
            if (naam.equalsIgnoreCase(klant.getAchternaam()) && voornaam.equals(klant.getVoornaam())) {
                return klant;
            }
        }
        return null;
    }

    public List<Klant> sorteerOpType() {
        List<Klant> klantType = new ArrayList<>(klantenLijst);
        klantType.sort(new Comparator<Klant>() {
            @Override
            public int compare(Klant o1, Klant o2) {
                return o1.getType().compareTo(o2.getType());
            }
        });
        return Collections.unmodifiableList(klantType);
    }
    public List<Klant> sorteerOpBtw() {
        List<Klant> klantBtw = new ArrayList<>(klantenLijst);
        klantBtw.sort(new Comparator<Klant>() {
            @Override
            public int compare(Klant o1, Klant o2) {
                return Double.compare(o1.getBtw(), (o2.getBtw()));
            }
        });
        return Collections.unmodifiableList(klantBtw);
    }
    public List<Klant> sorteerOpAanmaakDatum() {
        List<Klant> aanmaakDatum = new ArrayList<>(klantenLijst);
        aanmaakDatum.sort(new Comparator<Klant>() {
            @Override
            public int compare(Klant o1, Klant o2) {
                return o1.getAanmaakDatum().compareTo(o2.getAanmaakDatum());
            }
        });
        return Collections.unmodifiableList(aanmaakDatum);
    }

    public int getAantal() {
        return klantenLijst.size();
    }
}
