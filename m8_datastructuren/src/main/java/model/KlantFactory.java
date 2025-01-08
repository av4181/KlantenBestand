package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KlantFactory {

    private KlantFactory() {
        // Opdracht 2.2 - Private constructor om te voorkomen dat nieuwe KlantFactory objecten instanties kunnen gemaakt worden
    }

    // Static, constructor hoort bij de klasse niet bij de instanties
    public static Klant newEmptyKlant() {
        return new Klant();
    }

    // STATIC METHOD, HOORT BIJ DE KLASSE NIET BIJ EEN INSTANTIE
    public static Klant newFilledKlant(int id, String voornaam, String achternaam, String email, KlantType type, double btw, LocalDate aanmaakDatum, boolean redflag) {
        return new Klant(id, voornaam, achternaam, email, type, btw, aanmaakDatum, redflag);
    }

    // STATIC METHOD, HOORT BIJ DE KLASSE NIET BIJ EEN INSTANTIE
    public static Klant newRandomKlant() {
        Random random = new Random();
        int id = random.nextInt(1000);
        String voornaam = generateString(10, 1, true);
        String achternaam = generateString(15, 1, true);
        String email = voornaam.toLowerCase() + "." + achternaam.toLowerCase() + "@example.com";
        KlantType type = KlantType.values()[random.nextInt(KlantType.values().length)];
        double btw = random.nextDouble();
        LocalDate aanmaakDatum = LocalDate.ofYearDay(1900 + random.nextInt(100), 1 + random.nextInt(365));
        boolean redflag = random.nextBoolean();
        return new Klant(id, voornaam, achternaam, email, type, btw, aanmaakDatum, redflag);
    }

    // STATIC METHOD, HOORT BIJ DE KLASSE NIET BIJ EEN INSTANTIE
    private static String generateString(int maxWordLength, int wordCount, boolean camelCase) {
        Random random = new Random();
        String vowels = "aeiou";
        String consonants = "bcdfghjklmnpqrstvwxyz";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < wordCount; i++) {
            for (int j = 0; j < maxWordLength; j++) {
                String chars = random.nextInt(3) == 0 ? vowels : consonants;
                char c = chars.charAt(random.nextInt(chars.length()));
                if (j == 0 && camelCase) {
                    c = Character.toUpperCase(c);
                }
                builder.append(c);
            }
            builder.append(" ");
        }
        return builder.toString().trim();
    }

    private static LocalDate generateDate(){
        int minDay = (int) LocalDate.of(1800, 1,1).toEpochDay();
        int maxDay = (int) LocalDate.now().toEpochDay();
        long randomDay =  minDay + new Random().nextInt(maxDay - minDay);
        return LocalDate.ofEpochDay(randomDay);
    }
}
