import model.KlantFactory;
import model.Klanten;

import java.util.stream.Stream;

// Opdracht 4.3
public class Demo_12 {
    public static void main(String[] args) throws InterruptedException {
        Klanten klanten = new Klanten(10000);

        Runnable runnable = () -> Stream.generate(KlantFactory::newRandomKlant)
                .limit(5000)
                .forEach(klanten::voegToe);

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Na toevoegen door 2 threads met elk 5000 objecten: klanten = " + klanten.getAantal());
    }
}
