package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Objects;
import java.util.logging.Logger;

public class Klant implements Comparable<Klant> {
    //3.1 logger
    private static final Logger logger = Logger.getLogger(Klant.class.getName());
    private Long id;
    private String voornaam;
    private String achternaam;
    private String email;
    private KlantType type;
    // private List<Offerte> offertes;
    // private List<Bestelling> bestellingen;
    private Double btw;
    private LocalDate aanmaakDatum;
    private Boolean redflag;

    private static final String UNKNOWN = "Unknown";

    public Klant() {
        this(99999999L, UNKNOWN, UNKNOWN, UNKNOWN, KlantType.PARTICULIER, 0.12, LocalDate.of(1999, 01, 01), true);
    }

    public Klant(Long id, String voornaam, String achternaam, String email, KlantType type, Double btw, LocalDate aanmaakDatum, Boolean redflag) {
        setId(id);
        setVoornaam(voornaam);
        setAchternaam(achternaam);
        setEmail(email);
        setType(type);
        setBtw(btw);
        setAanmaakDatum(aanmaakDatum);
        setRedflag(redflag);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    //3.1 logger toevoegen
    public void setVoornaam(String voornaam) {
        if (voornaam == null || voornaam.isEmpty()) {
            logger.severe("Voornaam is een verplicht veld voor " + this);
        } else {
            this.voornaam = voornaam;
        }
    }

    public String getAchternaam() {
        return achternaam;
    }

    //3.1 logger toevoegen
    public void setAchternaam(String achternaam) {
        if (achternaam == null || achternaam.isEmpty()) {
            logger.severe("Achternaam is een verplicht veld voor " + this);
        } else {
            this.achternaam = achternaam;
        }
    }

    public String getEmail() {
        return email;
    }

    //3.1 logger toevoegen
    public void setEmail(String email) {
        if (email == null || email.isEmpty()) {
            logger.severe("E-mailadres is een verplicht veld voor " + this);
        } else {
            this.email = email;
        }
    }

    public KlantType getType() {
        return type;
    }

    //3.1 logger toevoegen
    public void setType(KlantType type) {
        if (type != KlantType.LEVERANCIER && type != KlantType.PARTICULIER) {
            logger.severe("Ongeldig klanttype " + type + " voor " + this);
        } else {
            this.type = type;
        }
    }

    public Double getBtw() {
        return btw;
    }

    //3.1 logger toevoegen
    public void setBtw(Double btw) {
        if (btw == null || btw < 0 || btw > 1) {
            logger.severe("Ongeldige BTW " + btw + " voor " + this);
        } else {
            this.btw = btw;
        }
    }

    public LocalDate getAanmaakDatum() {
        return aanmaakDatum;
    }

    //3.1 logger toevoegen
    public void setAanmaakDatum(LocalDate aanmaakDatum) {
        if (aanmaakDatum.isBefore(LocalDate.now())) {
            this.aanmaakDatum = aanmaakDatum;
        } else {
            logger.severe("Aanmaakdatum kan niet in de toekomst liggen");
        }
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