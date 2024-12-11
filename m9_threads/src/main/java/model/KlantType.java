package model;

public enum KlantType {

    PARTICULIER("PARTICULIER"), LEVERANCIER("LEVERANCIER");

    private String naam;

    KlantType(String naam) {
        this.naam = naam;
    }

    @Override
    public String toString() {
        return naam;
    }

}
