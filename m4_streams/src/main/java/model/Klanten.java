package model;

import java.util.*;
import java.util.function.Function;

/**
 * MULTIKLASSE
 */
public class Klanten {
    private TreeSet<Klant> klanten;

    public Klanten() {
        this.klanten = new TreeSet<>();
    }
    public boolean voegToe(Klant klant) {
        return klanten.add(klant);
    }
    public boolean verwijder(String achternaam, String voornaam) {
        return klanten.removeIf(klant -> achternaam.equalsIgnoreCase(klant.getAchternaam()) &&
                voornaam.equalsIgnoreCase(klant.getVoornaam()));
    }
    public Klant zoek(String achternaam, String voornaam) {
        return klanten.stream()
                .filter(klant -> achternaam.equalsIgnoreCase(klant.getAchternaam()) && voornaam.equals(klant.getVoornaam()))
                .findFirst()
                .orElse(null);
    }
    public List<Klant> sortedBy(Function<Klant, Comparable> function) {
        List<Klant> gesorteerdeKlanten = new ArrayList<>(klanten);
        gesorteerdeKlanten.sort(Comparator.comparing(function));
        return Collections.unmodifiableList(gesorteerdeKlanten);
    }
    public int getAantal() {
        return klanten.size();
    }
}
