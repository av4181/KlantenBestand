import model.Klant;
import model.KlantType;
import threading.KlantCallable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Predicate;

public class Demo_11 {

    private static final int TEST_COUNT = 100;

    public static void main(String[] args) throws Exception {
        // 4.2 a) we maken streams via de call methode van KlantCallable (Klant instance) en met 3 predicates:
        // het moeten particulieren zijn, btw > 0.12 en aangemaakt voor het jaar 2000
        // 3 callables maken
        Predicate<Klant> gewichtPredicate = a -> a.getType() == KlantType.PARTICULIER;
        Predicate<Klant> disciplinePredicate = a -> a.getBtw() > 0.12;
        Predicate<Klant> lengtePredicate = a -> a.getAanmaakDatum().getYear() < 2000;

        KlantCallable callable1 = new KlantCallable(gewichtPredicate);
        KlantCallable callable2 = new KlantCallable(disciplinePredicate);
        KlantCallable callable3 = new KlantCallable(lengtePredicate);

        // 4.2 b) threadpool gebruiken
        ExecutorService pool = Executors.newFixedThreadPool(3);


        Future[] futures = new Future[3];
        KlantCallable[] callables = {callable1, callable2, callable3};

        double gemiddelde = 0;

        for (int i = 0; i < TEST_COUNT; i++) {
            double startTijd = System.currentTimeMillis();

            for (int j = 0; j < futures.length; j++) {
                futures[j] = pool.submit(callables[j]);
            }
            try {
                futures[0].get();
                futures[1].get();
                futures[2].get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            gemiddelde += System.currentTimeMillis() - startTijd;
        }

        pool.shutdown();

        gemiddelde = gemiddelde / TEST_COUNT;
        System.out.printf("3 Futures verzamelen elk 1000 klanten (gemiddelde uit %.0f runs): %.2fms", TEST_COUNT, gemiddelde);
    }
}
