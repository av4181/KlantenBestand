package threading;

import model.Klant;
import model.KlantFactory;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

// Opdracht 2.1
public class KlantRunnable implements Runnable {
    private final Predicate<Klant> predicate;
    private List<Klant> klanten;

    public KlantRunnable(Predicate<Klant> predicate) {
        this.predicate = predicate;
    }

    @Override
    public void run() {
        this.klanten = Stream.generate(KlantFactory::newRandomKlant)
                .filter(predicate)
                .limit(1000)
                .toList();
    }

    public List<Klant> getKlanten() {
        return klanten;
    }
}
