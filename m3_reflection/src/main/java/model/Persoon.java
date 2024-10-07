package model;
import reflection.CanRun;

/**
 * De klasse Persoon is een superklasse die als generalisatie dient voor de klasse Klant.
 * @author Van Loon Andreas
 * @version 1.0
 */
public class Persoon {
    private String voornaam;
    private String achternaam;
    private String email;

    public Persoon(String voornaam, String achternaam, String email) {
        setVoornaam(voornaam);
        setAchternaam(achternaam);
        setEmail(email);
    }
    public String getVoornaam() {
        return voornaam;
    }

    @CanRun("Peter")
    public void setVoornaam(String voornaam) {
        if (voornaam == null || voornaam.isEmpty()) {
            throw new IllegalArgumentException("Voornaam is een verplicht veld");
        }
        this.voornaam = voornaam;
    }

    @CanRun
    public String getAchternaam() {
        return achternaam;
    }

    @CanRun ("Peeters")
    public void setAchternaam(String achternaam) {
        if (achternaam == null || achternaam.isEmpty()) {
            throw new IllegalArgumentException("Achternaam is een verplicht veld");
        }
        this.achternaam = achternaam;
    }

    public String getEmail() {
        return email;
    }

    @CanRun("example@hotmail.com")
    public void setEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("E-mailadres is een verplicht veld");
        }
        this.email = email;
    }
    @Override
    public String toString() {
        return String.format("%-15s %-10s %-12s", voornaam, achternaam, email);
    }
}
