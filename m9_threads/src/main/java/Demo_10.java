import model.Klant;
import model.KlantFactory;
import model.KlantType;
import threading.KlantAttacker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Demo_10 {

    public static void main(String[] args) {
        //opdracht 3.3
        List<Klant> klanten = Stream
                .generate(KlantFactory::newRandomKlant)
                .limit(1000)
                .collect(Collectors.toList());

        Predicate<Klant> particulierPredicate = a -> a.getType() == KlantType.PARTICULIER;
        Predicate<Klant> btwPredicate = a -> a.getBtw() > 0.12;
        Predicate<Klant> datumPredicate = a -> a.getAanmaakDatum().getYear() < 2000;

        long particulierCount = klanten.stream().filter(particulierPredicate).count();
        long btwCount = klanten.stream().filter(btwPredicate).count();
        long datumCount = klanten.stream().filter(datumPredicate).count();

        System.out.printf("Voor attacks resteren er nog <%d> klanten in de lijst, waarvan:", klanten.size());
        System.out.println("Aantal particulieren: " + particulierCount);
        System.out.println("Aantal klanten met hoge BTW: " + btwCount);
        System.out.println("Aantal oude klanten: " + datumCount);

        Thread attacker1 = new Thread(new KlantAttacker(klanten, particulierPredicate), "ATTACKER1");
        Thread attacker2 = new Thread(new KlantAttacker(klanten, btwPredicate), "ATTACKER2");
        Thread attacker3 = new Thread(new KlantAttacker(klanten, datumPredicate), "ATTACKER3");

        attacker1.start();
        attacker2.start();
        attacker3.start();

        try {
            attacker1.join();
            attacker2.join();
            attacker3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.printf("Na attacks resteren er nog <%d> klanten in de lijst, waarvan:", klanten.size());
        System.out.printf("\n<%d> Aantal particulieren:", klanten.stream().filter(particulierPredicate).count());
        System.out.printf("\n<%d> Aantal klanten met hoge BTW:", klanten.stream().filter(btwPredicate).count());
        System.out.printf("\n<%d> Aantal oude klanten:\n", klanten.stream().filter(datumPredicate).count());
    }
}