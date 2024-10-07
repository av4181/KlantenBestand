package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Objects;

public class Klant extends Persoon implements Comparable<Klant>{

    private Long id;
    private KlantType type;
    // private List<Offerte> offertes;
    // private List<Bestelling> bestellingen;
    private Double btw;
    private LocalDate aanmaakDatum;
    private Boolean redflag;

    private static final String UNKNOWN = "Unknown";

    public Klant() {
        this(99999999L,UNKNOWN,UNKNOWN,UNKNOWN,KlantType.PARTICULIER,0.12, LocalDate.now(),true);
    }

    public Klant(Long id, String voornaam, String achternaam, String email, KlantType type, Double btw, LocalDate aanmaakDatum, Boolean redflag) {
        super(voornaam,achternaam,email);
        setId(id);
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
        if (aanmaakDatum.isBefore(LocalDate.now())) {
            this.aanmaakDatum = aanmaakDatum;
        } else {
            throw new IllegalArgumentException("Aanmaakdatum kan niet in de toekomst liggen");
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
        return String.format("%-10s %3.2f%%\taanmaakdatum: %-12s Redflag: %-25s", type, btw, formatAanmaakDatum, redflag);
    }

}
