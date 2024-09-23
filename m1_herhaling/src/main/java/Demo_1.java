import data.Data;
import model.Klant;
import model.KlantType;
import model.Klanten;

import java.time.LocalDate;
import java.util.List;

public class Demo_1 {

    public static void main(String[] args) {

        List<Klant> data = Data.getData();
        Klanten klanten = new Klanten();

        System.out.println("Klanten data:");
        for (Klant klant : data) {
            klanten.voegToe(klant);
            System.out.println(klant);
        }
        System.out.println();

        System.out.print("Dubbele klant toevoegen: ");
        System.out.println(klanten.voegToe(new Klant(010101L, "Jef", "Peeters", "jefpeeters@hotmail.com",
                KlantType.PARTICULIER, 12.0, LocalDate.of(1994, 3, 11), Boolean.FALSE)) + "\n");

        System.out.println("Klant zoeken op type: ");
        System.out.println(klanten.zoek("Jef", "Peeters") + "\n");

        System.out.print("Aantal klanten: ");
        System.out.println(klanten.getAantal());

        System.out.print("Verwijder klant: ");
        System.out.println(klanten.verwijder("Jef", "Peeters"));

        System.out.print("Aantal klanten: ");
        System.out.println(klanten.getAantal() + "\n");

        klanten.voegToe(new Klant(123456L, "Inge", "Aerts",
                "inge.Aerts@gmail.com", KlantType.PARTICULIER, 0.12, LocalDate.of(1994, 3, 11), Boolean.FALSE));

        data = klanten.sorteerOpType();
        System.out.println("Klanten gesorteerd op type:");
        for (Klant klant : data) {
            System.out.println(klant);
        }
        System.out.println("test");
    }
}

