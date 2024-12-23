package data;

import model.Klant;
import model.KlantType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Data {
    private static List<Klant> data = new ArrayList<>();

    // Aanpassing 3.1 ivm ID Long ipv integer
    public static List<Klant> getData() {
        data.add(new Klant(0123465, "Peter", "Janssens",
                "peterjanssens@yahoo.com", KlantType.PARTICULIER, 0.12, LocalDate.of(1994, 3, 11), Boolean.FALSE));
        data.add(new Klant(1234567, "Jan", "de Vries",
                "jandevries@gmail.com", KlantType.PARTICULIER, 0.06, LocalDate.of(1985, 8, 22), Boolean.TRUE));
        data.add(new Klant(2345678, "Maria", "Smit",
                "mariasmit@hotmail.com", KlantType.LEVERANCIER, 0.21, LocalDate.of(1978, 1, 15), Boolean.FALSE));
        data.add(new Klant(3456789, "Emma", "van Dijk",
                "emmavandijk@outlook.com", KlantType.PARTICULIER, 0.21, LocalDate.of(1990, 6, 30), Boolean.TRUE));
        data.add(new Klant(4567890, "Mohammed", "El Idrissi",
                "mohammed.el.idrissi@gmail.com", KlantType.PARTICULIER, 0.00, LocalDate.of(1982, 11, 5), Boolean.FALSE));
        data.add(new Klant(5678901, "Sophie", "de Jong",
                "sophiedejong@yahoo.com", KlantType.PARTICULIER, 0.12, LocalDate.of(2001, 4, 12), Boolean.TRUE));
        data.add(new Klant(6789012, "David", "Cohen",
                "davidcohen@hotmail.com", KlantType.LEVERANCIER, 0.06, LocalDate.of(1965, 9, 28), Boolean.FALSE));
        data.add(new Klant(7890123, "Anna", "Peters",
                "annapeters@gmail.com", KlantType.PARTICULIER, 0.21, LocalDate.of(1998, 7, 3), Boolean.TRUE));
        data.add(new Klant(8901234, "Lisa", "de Wit",
                "lisa.de.wit@outlook.com", KlantType.LEVERANCIER, 0.00, LocalDate.of(1970, 2, 19), Boolean.FALSE));
        data.add(new Klant(9012345, "Ahmed", "Yilmaz",
                "ahmed.yilmaz@yahoo.com", KlantType.LEVERANCIER, 0.12, LocalDate.of(1995, 12, 25), Boolean.TRUE));


        return Collections.unmodifiableList(data);
    }
}
