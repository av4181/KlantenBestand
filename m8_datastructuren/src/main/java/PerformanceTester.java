import kollections.Kollections;
import kollections.LinkedList;
import kollections.ArrayList;
import kollections.List;
import model.Klant;
import model.KlantFactory;
import model.KlantType;

public class PerformanceTester {

    //change this method for own use
    // Opletten hier dat je List<Klant> je eigen List interface neemt
    // java.util.list overal verwijderen !!
    public static List<Klant> randomList(int n) {
        List<Klant> myList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            myList.add(KlantFactory.newRandomKlant());
        }
        return myList;
    }

    public static void compareArrayListAndLinkedList(int n) {
        //verwijder overal java.util.*
        List<Klant> myLinkedList = new LinkedList<>();
        List<Klant> myArrayList = new ArrayList<>();

        long startTimeAddToArray = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            myArrayList.add(0, KlantFactory.newRandomKlant());
        }
        //new Random().ints(n).forEach(i -> myLinkedList.add(KlantFactory.newRandomKlant()));
        long durationAddToArrayList = System.currentTimeMillis() - startTimeAddToArray;
        System.out.println("Adding " + n + " to ArrayList: " + durationAddToArrayList + " ms");

        long startTimeAddToLinked = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            myLinkedList.add(0, KlantFactory.newRandomKlant());
        }
        //new Random().ints(n).forEach(i -> myLinkedList.add(KlantFactory.newRandomKlant()));
        long durationAddToLinkedList = System.currentTimeMillis() - startTimeAddToLinked;
        System.out.println("Adding " + n + " to LinkedList: " + durationAddToLinkedList + " ms");

        //get at end
        long startTimeGetArray = System.currentTimeMillis();
        Klant getKlantFromArrayList = myArrayList.get(n - 1);
        long durationGetArrayList = System.currentTimeMillis() - startTimeGetArray;
        System.out.println("Getting " + n + " from ArrayList: " + durationGetArrayList + " ms");

        long startTimeGetLinked = System.currentTimeMillis();
        Klant getKlantFromLinkedList = myLinkedList.get(n - 1);
        long durationGetLinkedList = System.currentTimeMillis() - startTimeGetLinked;
        System.out.println("Getting " + n + " from LinkedList: " + durationGetLinkedList + " ms");
    }

    public static void testSelectionSort() {
        //test selectionsort for (int n = 1000; n < 20000; n += 1000)
        for (int n = 100; n < 2000; n += 100) {
            List<Klant> list = randomList(n);
            long startTime = System.currentTimeMillis();
            Kollections.selectionSort(list);
            long endTime = System.currentTimeMillis();
            System.out.println("Selection sort for n = " + n + ": " + (endTime - startTime) + " ms");
        }
    }

    public static void testMergeSort() {
        //test mergesort for (int n = 1000; n < 200000; n += 1000)
        for (int n = 100; n < 2000; n += 100) {
            List<Klant> list = randomList(n);
            long startTime = System.currentTimeMillis();
            Kollections.mergeSort(list);
            long endTime = System.currentTimeMillis();
            System.out.println("Merge sort for n = " + n + ": " + (endTime - startTime) + " ms");
        }
    }
}
