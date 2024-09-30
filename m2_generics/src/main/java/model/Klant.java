package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Objects;

/**
 * De klasse Klant beschrijft een klant zoals deze in een klantenbestand zou voorkomen.
 * @author Van Loon Andreas
 * @version 1.0
 * @see <a href="https://www.klantenbestand.com"> Een voorbeeld pagina van het uiteindelijke resultaat</a>
 */
public class Klant implements Comparable<Klant>{
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
    /**
     * Default constructor met default waardes voor alle attributen
     */
    public Klant() {
        this(99999999L,UNKNOWN,UNKNOWN,UNKNOWN,KlantType.PARTICULIER,0.12, LocalDate.now(),true);
    }
    /**
     * Constructor die toelaat een object op maat te maken
     * @param id Een ID die de klant uniek maakt
     * @param voornaam De voornaam van de klant
     * @param achternaam De achternaam van de klant
     * @param email De email van de klant
     * @param type Het type van klant
     * @param btw Het BTW tarief waaronder de klant valt
     * @param aanmaakDatum De datum waarop de klant werd toegevoegd in het klantenbestand
     * @param redflag Een boolean die weergeeft of de klant moet in de gaten worden gehouden
     */
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
    /**
     * @return De ID van de klant
     */
    public Long getId() {
        return id;
    }
    /**
     * Zet de voornaam van de klant.  De voornaam van een klant kan niet leeg zijn.
     * @param id ID van de klant
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * @return De voornaam van de klant
     */
    public String getVoornaam() {
        return voornaam;
    }
    /**
     * Zet de voornaam van de klant.  De voornaam van een klant kan niet leeg zijn.
     * @param voornaam Voornaam van de klant
     * @throws IllegalArgumentException indien de naam een leeg veld is
     */
    public void setVoornaam(String voornaam) {
        if (voornaam == null || voornaam.isEmpty()) {
            throw new IllegalArgumentException("Voornaam is een verplicht veld");
        }
        this.voornaam = voornaam;
    }
    /**
     * @return De achternaam van de klant
     */
    public String getAchternaam() {
        return achternaam;
    }
    /**
     * Zet de achternaam van de klant.  De voornaam van een klant kan niet leeg zijn.
     * @param achternaam Achternaam van de klant
     * @throws IllegalArgumentException indien de achternaam een leeg veld is
     */
    public void setAchternaam(String achternaam) {
        if (achternaam == null || achternaam.isEmpty()) {
            throw new IllegalArgumentException("Achternaam is een verplicht veld");
        }
        this.achternaam = achternaam;
    }
    /**
     * @return De email van de klant
     */
    public String getEmail() {
        return email;
    }
    /**
     * Zet de email van de klant.  De email van een klant kan niet leeg zijn.
     * @param email Email van de klant
     * @throws IllegalArgumentException indien de email een leeg veld is
     */
    public void setEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("E-mailadres is een verplicht veld");
        }
        this.email = email;
    }
    /**
     * @return Het type van de klant van de klant
     * @see KlantType Een enum die het type van de klant definieert
     */
    public KlantType getType() {
        return type;
    }
    /**
     * Zet het type van de klant.  Het type van een klant kan niet leeg zijn.
     * @param type Type van de klant
     * @throws IllegalArgumentException indien type iets anders is dan de toegalten waardes
     */
    public void setType(KlantType type) {
        if (type != KlantType.LEVERANCIER && type != KlantType.PARTICULIER) {
            throw new IllegalArgumentException("Alleen LEVERANCIER of PARTICULIER zijn toegestaan als klanttype");
        }
        this.type = type;
    }
    /**
     * @return Het BTW tarief van de klant
     */
    public Double getBtw() {
        return btw;
    }
    /**
     * Zet het BTW tarief van de klant.  Het BTW tarief van een klant kan niet leeg zijn.
     * @param btw BTW tarief van de klant
     * @throws IllegalArgumentException indien het BTW tarief niet tussen 0 en 1 ligt
     */
    public void setBtw(Double btw) {
        if (btw == null || btw < 0 || btw > 1) {
            throw new IllegalArgumentException("BTW moet een waarde tussen 0 en 1 zijn (inclusief)");
        }
        this.btw = btw;
    }
    /**
     * @return De datum van aanmaken van de klant
     */
    public LocalDate getAanmaakDatum() {
        return aanmaakDatum;
    }
    /**
     * Zet de aanmaakdatum van de klant.  De aanmaakdatum van een klant kan niet leeg zijn.
     * @param aanmaakDatum Aanmaakdatum van de klant
     * @throws IllegalArgumentException indien de aanmaakdatum in de toekomst ligt
     */
    public void setAanmaakDatum(LocalDate aanmaakDatum) {
        if (aanmaakDatum.isBefore(LocalDate.now())) {
            this.aanmaakDatum = aanmaakDatum;
        } else {
            throw new IllegalArgumentException("Aanmaakdatum kan niet in de toekomst liggen");
        }
    }
    /**
     * @return De redflag waarde van de klant
     */
    public Boolean getRedflag() {
        return redflag;
    }
    /**
     * Zet de redflag van de klant.
     * @param redflag Redflag waarde van de klant
     */
    public void setRedflag(Boolean redflag) {
        this.redflag = redflag;
    }
    /**
     * Check of 2 klanten gelijk zijn
     * @param o Te vergelijken object
     * @return True als de klanten gelijk zijn, false als de klanten niet gelijk zijn
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Klant klant = (Klant) o;
        return Objects.equals(id, klant.id);
    }
    /**
     * Hash wordt op de klant ID gezet
     * @return integer
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    /**
     * Compare op voor -en achternaam van de klant
     * @return integer
     */
    @Override
    public int compareTo(Klant o) {
        return Comparator.comparing(Klant::getAchternaam)
                .thenComparing(Klant::getVoornaam)
                .compare(this, o);
    }
    /**
     *
     * @return string van het gecreëerde object met de gebruikte parameters
     */
    @Override
    public String toString() {
        DateTimeFormatter fm = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formatAanmaakDatum = aanmaakDatum.format(fm);
        return String.format("%-15s %-10s %-12s %-10s %3.2f%%\taanmaakdatum: %-12s Redflag: %-25s", voornaam, achternaam, email,
                type, btw, formatAanmaakDatum, redflag);
    }
}
