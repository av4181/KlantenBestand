package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Objects;

public class Klant implements Comparable<Klant>{

    Long id;
    String voornaam;
    String achternaam;
    String email;
    KlantType type;
    // List<Offerte> offertes;
    // List<Bestelling> bestellingen;
    Double btw;
    LocalDate aanmaakDatum;
    Boolean redflag;

    private static final String UNKNOWN = "Unknown";

    public Klant() {
        this(99999999L,UNKNOWN,UNKNOWN,UNKNOWN,KlantType.PARTICULIER,0.12, LocalDate.now(),true);
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

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public KlantType getType() {
        return type;
    }

    public void setType(KlantType type) {
        this.type = type;
    }

    public Double getBtw() {
        return btw;
    }

    public void setBtw(Double btw) {
        this.btw = btw;
    }

    public LocalDate getAanmaakDatum() {
        return aanmaakDatum;
    }

    public void setAanmaakDatum(LocalDate aanmaakDatum) {
        this.aanmaakDatum = aanmaakDatum;
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
