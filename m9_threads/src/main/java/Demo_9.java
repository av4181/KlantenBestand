import model.KlantType;
import threading.KlantRunnable;


public class Demo_9 {

    private static final int TESTCOUNTER = 3;

    public static void main(String[] args) {
        KlantRunnable runnableKlant1 = new KlantRunnable(f -> f.getAchternaam().startsWith("A"));
        KlantRunnable runnableKlant2 = new KlantRunnable(f -> f.getType() == KlantType.PARTICULIER);
        KlantRunnable runnableKlant3 = new KlantRunnable(f -> f.getBtw() < 0.21);

// Opdracht 2.2
        long[] times = new long[TESTCOUNTER];
        for (int i = 0; i < TESTCOUNTER; i++) {
            Thread[] threads = new Thread[3];
            threads[0] = new Thread(runnableKlant1);
            threads[1] = new Thread(runnableKlant2);
            threads[2] = new Thread(runnableKlant3);
            long startTime = System.currentTimeMillis();
            for (Thread value : threads) {
                value.start();
            }

            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    //TODO:
                }
            }

            times[i] = System.currentTimeMillis() - startTime;
        }

        long totalTimeAverage = 0;

        for (long time : times) {
            totalTimeAverage += time;
            System.out.println(time);
        }

        long averageTime = totalTimeAverage / TESTCOUNTER;

        System.out.println("3 threads verzamelen elk 1000 Klanten (gemiddelde uit 100 runs): " + averageTime + " ms");
        System.out.println();
        System.out.println("Main end");
    }
}
