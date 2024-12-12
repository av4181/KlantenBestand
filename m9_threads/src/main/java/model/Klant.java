package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Objects;

public final class Klant implements Comparable<Klant>{

    // Opdracht 3.1
    private final Long id;
    private final String voornaam;
    private final String achternaam;
    private final String email;
    private final KlantType type;
    // private List<Offerte> offertes;
    // private List<Bestelling> bestellingen;
    private final Double btw;
    private final LocalDate aanmaakDatum;
    private final Boolean redflag;

    private static final String UNKNOWN = "Unknown";

    public Klant() {
        this(99999999L,UNKNOWN,UNKNOWN,UNKNOWN,KlantType.PARTICULIER,0.12, LocalDate.now(),true);
    }

    // Exceptions mee in de constructor steken
    public Klant(long id, String voornaam, String achternaam, String email, KlantType type, Double btw, LocalDate aanmaakDatum, Boolean redflag) {
        if (voornaam == null || voornaam.isEmpty()) {
            throw new IllegalArgumentException("Voornaam is een verplicht veld");
        }
        this.voornaam = voornaam;

        if (achternaam == null || achternaam.isEmpty()) {
            throw new IllegalArgumentException("Achternaam is een verplicht veld");
        }
        this.achternaam = achternaam;

        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("E-mailadres is een verplicht veld");
        }
        this.email = email;

        if (type != KlantType.LEVERANCIER && type != KlantType.PARTICULIER) {
            throw new IllegalArgumentException("Alleen LEVERANCIER of PARTICULIER zijn toegestaan als klanttype");
        }
        this.type = type;

        if (btw == null || btw < 0 || btw > 1) {
            throw new IllegalArgumentException("BTW moet een waarde tussen 0 en 1 zijn (inclusief)");
        }
        this.btw = btw;

        if (aanmaakDatum == null || aanmaakDatum.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Aanmaakdatum moet in het verleden liggen");
        }
        this.aanmaakDatum = aanmaakDatum;

        this.id = id;
        this.redflag = redflag;
    }

    // 3.1 c) ENkel getters, geen setters
    public long getId() {
        return id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public String getEmail() {
        return email;
    }

    public KlantType getType() {
        return type;
    }

    public Double getBtw() {
        return btw;
    }

    public LocalDate getAanmaakDatum() {
        return aanmaakDatum; // LocalDate is immutable
    }

    public Boolean getRedflag() {
        return redflag;
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