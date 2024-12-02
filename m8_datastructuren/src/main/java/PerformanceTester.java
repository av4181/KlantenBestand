import kollections.Kollections;
import kollections.lists.LinkedList;
import kollections.lists.ArrayList;
import kollections.lists.List;
import kollections.maps.ListMap;
import kollections.maps.HashMap;
import kollections.maps.Map;
import kollections.sets.ArraySet;
import kollections.sets.TreeSet;
import model.Klant;
import model.KlantFactory;

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

    //Opdracht 5.8

    public static void compareListMapToHashMap(int mapSize) {
        ListMap<Klant, String> listMap = new ListMap<>();
        fillMap(listMap, mapSize);

        long startTime = System.nanoTime();
        for (int i = 0; i < mapSize; i++) {
            Klant klant = KlantFactory.newEmptyKlant();
            klant.setAchternaam("Klant" + i); // Gebruik achternaam als identificerend attribuut
            listMap.get(klant);
        }
        long duration = System.nanoTime() - startTime;
        int equalsCounterListMap = Klant.getEqualsCounter(); // Haal de equalsCounter op

        System.out.printf("Listmap --> n: %d equalscounter:%d  nanosec: %d\n", mapSize, equalsCounterListMap, duration);

        HashMap<Klant, String> hashMap = new HashMap<>();
        fillMap(hashMap, mapSize);

        startTime = System.nanoTime();
        for (int i = 0; i < mapSize; i++) {
            Klant klant = KlantFactory.newEmptyKlant();
            klant.setAchternaam("Klant" + i); // Gebruik achternaam als identificerend attribuut
            hashMap.get(klant);
        }
        duration = System.nanoTime() - startTime;
        int equalsCounterHashMap = Klant.getEqualsCounter() - equalsCounterListMap; // Bereken het verschil

        System.out.printf("Hashmap --> n: %d equalscounter:%d  nanosec: %d\n", mapSize, equalsCounterHashMap, duration);
    }

    private static void fillMap(Map<Klant, String> map, int N) {
        for (int i = 0; i < N; i++) {
            Klant klant = KlantFactory.newEmptyKlant();
            klant.setAchternaam("Klant" + i); // Gebruik achternaam als identificerend attribuut
            map.put(klant, "Value voor Klant" + i);
        }
    }

    //Opdracht 6.1

    public static void compareArraySetToTreeSet(int setSize) {
        ArraySet<Klant> arraySet = new ArraySet<>();

        long startTimeA = System.nanoTime();
        for (int i = 0; i < setSize; i++) {
            arraySet.add(KlantFactory.newRandomKlant());
        }
        long durationA = System.nanoTime() - startTimeA;

        int equalsCounterArraySet = Klant.getEqualsCounter();
        int compareCountArraySet = Klant.getCounter();

        System.out.printf("ArraySet --> n: %d equalscounter:%d  \n", setSize, equalsCounterArraySet);
        System.out.printf("ArraySet --> n: %d comparecounter:%d  \n", setSize, compareCountArraySet);
        System.out.printf("ArraySet --> n: %d nanosec: %d\n", setSize, durationA);

        TreeSet<Klant> treeSet = new TreeSet<>();

        long startTimet = System.nanoTime();
        for (int i = 0; i < setSize; i++) {
            treeSet.add(KlantFactory.newRandomKlant());
        }
        long durationt = System.nanoTime() - startTimet;

        int equalsCounterTreeSet = Klant.getEqualsCounter() - equalsCounterArraySet;
        int compareCountTreeSet = Klant.getCounter() - compareCountArraySet;

        System.out.printf("TreeSet --> n: %d equalscounter:%d  \n", setSize, equalsCounterTreeSet);
        System.out.printf("TreeSet --> n: %d comparecounter:%d  \n", setSize, compareCountTreeSet);
        System.out.printf("TreeSet --> n: %d nanosec: %d\n", setSize, durationt);
    }
}
