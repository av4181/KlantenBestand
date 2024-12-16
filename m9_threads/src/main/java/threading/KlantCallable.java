package threading;

import model.Klant;
import model.KlantFactory;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Predicate;
import java.util.stream.Stream;

// Opdracht 4.2
public class KlantCallable implements Callable<List<Klant>> {
    private final Predicate<Klant> predicate;

    public KlantCallable(Predicate<Klant> predicate) {
        this.predicate = predicate;
    }

    @Override
    public List<Klant> call() {
        return Stream.generate(KlantFactory::newRandomKlant)
                .filter(predicate)
                .limit(1000)
                .toList();
    }
}
