package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Objects;

public class Klant implements Comparable<Klant>, Serializable {
    // Aanpassingen 3.1
    private int id;
    private String voornaam;
    private String achternaam;
    private transient String email;
    private KlantType type;
    // private List<Offerte> offertes;
    // private List<Bestelling> bestellingen;
    private transient Double btw;
    private LocalDate aanmaakDatum;
    private transient Boolean redflag;

    private static final String UNKNOWN = "Unknown";

    public Klant() {
        this(-1,UNKNOWN,UNKNOWN,UNKNOWN,KlantType.PARTICULIER,0.12, LocalDate.now(),true);
    }

    //Aanpassing 3.1 ID als PK
    public Klant(int id, String voornaam, String achternaam, String email, KlantType type, Double btw, LocalDate aanmaakDatum, Boolean redflag) {
        this.id = id;
        setVoornaam(voornaam);
        setAchternaam(achternaam);
        setEmail(email);
        setType(type);
        setBtw(btw);
        setAanmaakDatum(aanmaakDatum);
        setRedflag(redflag);
    }

    // Extra constructor om ingave ID te omzeilen
    public Klant(String voornaam, String achternaam, String email, KlantType type, Double btw, LocalDate aanmaakDatum, Boolean redflag) {
        this(-1, voornaam, achternaam, email, type, btw, aanmaakDatum, redflag); // Roep de volledige constructor aan met een default id
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        if (voornaam == null || voornaam.isEmpty()) {
            throw new IllegalArgumentException("Voornaam is een verplicht veld");
        }
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        if (achternaam == null || achternaam.isEmpty()) {
            throw new IllegalArgumentException("Achternaam is een verplicht veld");
        }
        this.achternaam = achternaam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("E-mailadres is een verplicht veld");
        }
        this.email = email;
    }

    public KlantType getType() {
        return type;
    }

    public void setType(KlantType type) {
        if (type != KlantType.LEVERANCIER && type != KlantType.PARTICULIER) {
            throw new IllegalArgumentException("Alleen LEVERANCIER of PARTICULIER zijn toegestaan als klanttype");
        }
        this.type = type;
    }

    public Double getBtw() {
        return btw;
    }

    public void setBtw(Double btw) {
        if (btw == null || btw < 0 || btw > 1) {
            throw new IllegalArgumentException("BTW moet een waarde tussen 0 en 1 zijn (inclusief)");
        }
        this.btw = btw;
    }

    public LocalDate getAanmaakDatum() {
        return aanmaakDatum;
    }

    public void setAanmaakDatum(LocalDate aanmaakDatum) {
//        if (aanmaakDatum.isBefore(LocalDate.now())) {
            this.aanmaakDatum = aanmaakDatum;
//        } else {
//            throw new IllegalArgumentException("Aanmaakdatum kan niet in de toekomst liggen");
//        }
    }

    public Boolean getRedflag() {
        return redflag;
    }

    public void setRedflag(Boolean redflag) {
        this.redflag = redflag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Klant klant = (Klant) o;
        return Objects.equals(id, klant.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    @Override
    public int compareTo(Klant o) {
        return Comparator.comparing(Klant::getAchternaam)
                .thenComparing(Klant::getVoornaam)
                .compare(this, o);
    }
    @Override
    public String toString() {
        DateTimeFormatter fm = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formatAanmaakDatum = aanmaakDatum.format(fm);
        return String.format("%-15s %-10s %-12s %-10s %3.2f%%\taanmaakdatum: %-12s Redflag: %-25s", voornaam, achternaam, email,
                type, btw, formatAanmaakDatum, redflag);
    }

}