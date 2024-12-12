package threading;

import model.Klant;

import java.util.List;
import java.util.function.Predicate;

// Opdracht 3.2
public class KlantAttacker implements Runnable {
    private final List<Klant> klantenLijst;
    // Geviseerde ijst van klanten objecten die wordt aangevallen
    private final Predicate<Klant> predicate;

    public KlantAttacker(List<Klant> klantenLijst, Predicate<Klant> predicate) {
        this.klantenLijst = klantenLijst;
        this.predicate = predicate;
    }

    // Opdracht 3.2 c)
    // Opdracht 3.4
    @Override
    public void run() {
        synchronized (klantenLijst){
                klantenLijst.removeIf(predicate);
        }
    }
}
