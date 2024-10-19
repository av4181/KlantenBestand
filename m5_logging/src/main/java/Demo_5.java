import model.Klant;
import model.KlantType;
import model.Klanten;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.logging.LogManager;

public class Demo_5 {
    //3.2
    public static void main(String[] args) throws IOException {
//        Klant klant1 = new Klant(1L, "", "Janssens", "jan.janssens@example.com",
//                KlantType.PARTICULIER, 0.21, LocalDate.now(), false);
//        Klant klant2 = new Klant(2L, "Piet", "Pieters", "piet.pieters@example.com",
//                null, 0.06, LocalDate.of(1985, 12, 20), true);
//        Klant klant3 = new Klant(3L, "Anna", "Ankers", "anna.ankers@example.com",
//                KlantType.PARTICULIER, 2.0, LocalDate.of(1995, 8, 15), true);
        //3.2
        Klant klant = new Klant();
        System.out.println(klant.getEmail());
        klant.setBtw(500D);
        klant.setAanmaakDatum(LocalDate.of(2025, 01, 01));

        //3.6
        //java -Djava.util.logging.config.file=../m5_logging/src/main/resources/logging.properties Demo_5
        InputStream inputStream = Demo_5.class.getResourceAsStream("logging.properties");
        LogManager.getLogManager().readConfiguration(inputStream);

        //3.6 Foute klanten - PROBLEMEN MET DATUMS
        Klanten klanten = new Klanten();
//        Klant klant1 = new Klant(1L, "", "Janssens", "jan.janssens@example.com",
//                KlantType.PARTICULIER, 0.21, LocalDate.of(1990, 5, 10), false);
//        Klant klant2 = new Klant(2L, "Piet", "Pieters", "piet.pieters@example.com",
//                null, 0.06, LocalDate.of(1985, 12, 20), true);
//        Klant klant3 = new Klant(3L, "Anna", "Ankers", "anna.ankers@example.com",
//                KlantType.PARTICULIER, 2.0, LocalDate.of(1995, 8, 15), true);

        //3.6 Correcte klanten - PROBLEMEN MET DATUMS
//        Klant klant4 = new Klant(4L, "Kees", "Keesen", "kees.keesen@example.com",
//                KlantType.LEVERANCIER, 0.21, LocalDate.of(1980, 3, 5), false);
//        Klant klant5 = new Klant(5L, "Piet", "Janssens", "piet.janssens@example.com",
//                KlantType.PARTICULIER, 0.06, LocalDate.of(2000, 1, 1), true);
        Klant klant1 = new Klant(0123465L, "Peter", "Janssens",
                "peterjanssens@yahoo.com", KlantType.PARTICULIER,
                0.12, LocalDate.of(1994, 3, 11), Boolean.FALSE);

        //3.6 Toevoegen en verwijderen
        klanten.voegToe(klant1);
        klanten.voegToe(klant);
        klanten.verwijder("Janssens", "Peter");

    }
}
