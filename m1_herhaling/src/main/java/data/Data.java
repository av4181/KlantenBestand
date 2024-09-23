package data;

import model.Klant;
import model.KlantType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Data {
    private static List<Klant> data = new ArrayList<>();

    public static List<Klant> getData() {
        data.add(new Klant(0123465L, "Peter", "Janssens",
                "peterjanssens@yahoo.com", KlantType.PARTICULIER, 0.12, LocalDate.of(1994, 3, 11), Boolean.FALSE));
        data.add(new Klant(1234567L, "Jan", "de Vries",
                "jandevries@gmail.com", KlantType.PARTICULIER, 0.06, LocalDate.of(1985, 8, 22), Boolean.TRUE));
        data.add(new Klant(2345678L, "Maria", "Smit",
                "mariasmit@hotmail.com", KlantType.PARTICULIER, 0.21, LocalDate.of(1978, 1, 15), Boolean.FALSE));

        return Collections.unmodifiableList(data);
    }
}
