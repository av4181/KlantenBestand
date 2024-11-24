import kollections.Kollections;
import kollections.LinkedList;
import kollections.ArrayList;
import model.Klant;
import model.KlantType;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class PerformanceTester {

    //change this method for own use
    public static List<Klant> randomList(int n) {
        List<Klant> myList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            myList.add(new Klant(i, "Voornaam" + i, "Achternaam" + i, "email" + i + "@example.com", KlantType.values()[random.nextInt(KlantType.values().length)], random.nextDouble(), LocalDate.now(), random.nextBoolean()));
        }
        return myList;
    }

    public static void compareArrayListAndLinkedList(int n) {
        List<Klant> arrayList = new ArrayList<>(randomList(n));
        List<Klant> linkedList = new LinkedList<>(randomList(n));

        // Add at beginning
        //use System.currentTimeMillis to compare performance of ArrayList en LinkedList
        long startTime = System.currentTimeMillis();
        arrayList.add(0, new Klant());
        long endTime = System.currentTimeMillis();
        System.out.println("ArrayList add at beginning: " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        linkedList.add(0, new Klant());
        endTime = System.currentTimeMillis();
        System.out.println("LinkedList add at beginning: " + (endTime - startTime) + " ms");

        // Get at end
        //use System.currentTimeMillis to compare performance of ArrayList en LinkedList
        startTime = System.currentTimeMillis();
        arrayList.get(arrayList.size() - 1);
        endTime = System.currentTimeMillis();
        System.out.println("ArrayList get at end: " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        linkedList.get(linkedList.size() - 1);
        endTime = System.currentTimeMillis();
        System.out.println("LinkedList get at end: " + (endTime - startTime) + " ms");
    }

    public static void testSelectionSort() {
        //test selectionsort for (int n = 1000; n < 20000; n += 1000)
        for (int n = 1000; n < 20000; n += 1000) {
            List<Klant> list = randomList(n);
            long startTime = System.currentTimeMillis();
            Kollections.selectionSort(list);
            long endTime = System.currentTimeMillis();
            System.out.println("Selection sort for n = " + n + ": " + (endTime - startTime) + " ms");
        }
    }

    public static void testMergeSort() {
        //test mergesort for (int n = 1000; n < 200000; n += 1000)
        for (int n = 1000; n < 200000; n += 1000) {
            List<Klant> list = randomList(n);
            long startTime = System.currentTimeMillis();
            Kollections.mergeSort(list);
            long endTime = System.currentTimeMillis();
            System.out.println("Merge sort for n = " + n + ": " + (endTime - startTime) + " ms");
        }
    }
}
