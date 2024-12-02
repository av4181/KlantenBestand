package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Objects;

public class Klant implements Comparable<Klant>{

    //Opdracht 2.1 ... Maak alles package private.  Attributen basisklasse zijn niet meer bereikbaar
    private int Id;
    private String Voornaam;
    private String Achternaam;
    private String Email;
    private KlantType Type;
    // private List<Offerte> offertes;
    // private List<Bestelling> bestellingen;
    private Double Btw;
    private LocalDate AanmaakDatum;
    private Boolean Redflag;

    private static final String UNKNOWN = "Unknown";

    // Counters voor opdracht 5.8
    private static int Counter;
    private static int equalsCounter;

    // Opdracht 2.1
    Klant() {
        this(999999,UNKNOWN,UNKNOWN,UNKNOWN,KlantType.PARTICULIER,0.12, LocalDate.now(),true);
    }

    // Opdracht 2.1
    Klant(int id, String voornaam, String achternaam, String email, KlantType type, Double btw, LocalDate aanmaakDatum, Boolean redflag) {
        Id = id;
        Voornaam=voornaam;
        Achternaam=achternaam;
        Email=email;
        Type=type;
        Btw=btw;
        AanmaakDatum=aanmaakDatum;
        Redflag=redflag;
    }

    public static int getEqualsCounter() {
        return equalsCounter;
    }

    public static int getCounter() {
        return Counter;
    }

    public int getId() {
        return Id;
    }

//    public void setId(int id) {
//        this.Id = id;
//    }

    public String getVoornaam() {
        return Voornaam;
    }

//    public void setVoornaam(String voornaam) {
//        if (voornaam == null || voornaam.isEmpty()) {
//            throw new IllegalArgumentException("Voornaam is een verplicht veld");
//        }
//        this.Voornaam = voornaam;
//    }

    public String getAchternaam() {
        return Achternaam;
    }

    public void setAchternaam(String achternaam) {
        if (achternaam == null || achternaam.isEmpty()) {
            throw new IllegalArgumentException("Achternaam is een verplicht veld");
        }
        this.Achternaam = achternaam;
    }

    public String getEmail() {
        return Email;
    }

//    public void setEmail(String email) {
//        if (email == null || email.isEmpty()) {
//            throw new IllegalArgumentException("E-mailadres is een verplicht veld");
//        }
//        this.Email = email;
//    }

    public KlantType getType() {
        return Type;
    }

//    public void setType(KlantType type) {
//        if (type != KlantType.LEVERANCIER && type != KlantType.PARTICULIER) {
//            throw new IllegalArgumentException("Alleen LEVERANCIER of PARTICULIER zijn toegestaan als klanttype");
//        }
//        this.Type = type;
//    }

    public Double getBtw() {
        return Btw;
    }

//    public void setBtw(Double btw) {
//        if (btw == null || btw < 0 || btw > 1) {
//            throw new IllegalArgumentException("BTW moet een waarde tussen 0 en 1 zijn (inclusief)");
//        }
//        this.Btw = btw;
//    }

    public LocalDate getAanmaakDatum() {
        return AanmaakDatum;
    }

    public void setAanmaakDatum(LocalDate aanmaakDatum) {
//        if (aanmaakDatum.isBefore(LocalDate.now())) {
            this.AanmaakDatum = aanmaakDatum;
//        } else {
//            throw new IllegalArgumentException("Aanmaakdatum kan niet in de toekomst liggen");
//        }
    }

    public Boolean getRedflag() {
        return Redflag;
    }

//    public void setRedflag(Boolean redflag) {
//        this.redflag = redflag;
//    }

    //Opdracht 5.7
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Klant klant = (Klant) o;
        return Objects.equals(Achternaam, klant.Achternaam); // Vergelijking op achternaam
    }

    @Override
    public int hashCode() {
        return Objects.hash(Achternaam); // Hashcode gebaseerd op achternaam
    }

    @Override
    public int compareTo(Klant o) {

        return this.Achternaam.compareTo(o.Achternaam); // Vergelijking op achternaam
    }

    @Override
    public String toString() {
        DateTimeFormatter fm = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formatAanmaakDatum = AanmaakDatum.format(fm);
        return String.format("%-15s %-10s %-12s %-10s %3.2f%%\taanmaakdatum: %-12s Redflag: %-25s", Voornaam, Achternaam, Email,
                Type, Btw, formatAanmaakDatum, Redflag);
    }

}