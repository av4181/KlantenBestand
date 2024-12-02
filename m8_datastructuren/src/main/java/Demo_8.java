import kollections.Kollections;
import kollections.lists.List;
import model.Klant;
import model.KlantFactory;
import model.KlantType;

import java.time.LocalDate;

import java.util.stream.Stream;

public class Demo_8 {

    public static void main(String[] args) {

        System.out.println("Groeiproject week 8:");
        System.out.println("Empty klant:");
        System.out.println(KlantFactory.newEmptyKlant() + "\n");

        System.out.println("Filled klant:");
        System.out.println(KlantFactory.newFilledKlant(1, "Jan", "Janssens",
                "jan.janssens@example.com", KlantType.PARTICULIER, 0.21,
                LocalDate.of(1990, 5, 10), false));
        System.out.println();

        System.out.println("30 randomly generated stadium sorted by name:");
        Stream.generate(KlantFactory::newRandomKlant)
                .limit(30)
                .forEach(System.out::println);


//        PerformanceTester.compareArrayListAndLinkedList(100000);
//        List<Klant> myArrayList = new ArrayList<>();
//        myArrayList.add(new Klant(0123465, "Peter", "Janssens", "peterjanssens@yahoo.com",
//                KlantType.PARTICULIER, 0.12, LocalDate.of(1994, 3, 11), Boolean.FALSE));
//        myArrayList.add(new Klant(1234567, "Jan", "de Vries", "jandevries@gmail.com",
//                KlantType.PARTICULIER, 0.06, LocalDate.of(1985, 8, 22), Boolean.TRUE));
//        myArrayList.add(new Klant(2345678L, "Maria", "Smit", "mariasmit@hotmail.com",
//                KlantType.LEVERANCIER, 0.21, LocalDate.of(1978, 1, 15), Boolean.FALSE));
//        myArrayList.add(new Klant(3456789L, "Emma", "van Dijk", "emmavandijk@outlook.com",
//                KlantType.PARTICULIER, 0.21, LocalDate.of(1990, 6, 30), Boolean.TRUE));
//        for (int i = 0; i < 1000; i++) {
//            myArrayList.add(new Klant(new Random().nextInt(1000), "klant" + i));
//        }
//        Kollections.quickSort(myArrayList);
//        System.out.println("After sort:");
//        for (int i=0;i<myArrayList.size();i++){
//            System.out.println(myArrayList.get(i));
//        }
//        Klant klantToFind = new Klant(1234567L, "Jan", "de Vries", "jandevries@gmail.com",
//                KlantType.PARTICULIER, 0.06, LocalDate.of(1985, 8, 22), Boolean.TRUE);
//        System.out.println("Index of student " + klantToFind + ": " + Kollections.lineairSearch(myArrayList, klantToFind));
//        System.out.println("Index of student " + klantToFind + ": " +  Kollections.binarySearch(myArrayList, klantToFind));
//        System.out.println("==========SELECTIONSORT================");
//        PerformanceTester.testSelectionSort();
//        System.out.println("==========MERGESORT====================");
//        PerformanceTester.testMergeSort();
//

        //
//
        System.out.println();
        System.out.println("3.2 Instantie eigen arraylist");
        System.out.println();


        //Create a random list for  testing
        List<Klant> randomCustomList = PerformanceTester.randomList(30);

        System.out.println("all");
        for (int i = 0; i < randomCustomList.size(); i++) {
            System.out.println(randomCustomList.get(i));
        }

        randomCustomList.add(0, KlantFactory.newEmptyKlant());

        System.out.println("It still works after we do an add:");
        System.out.println(randomCustomList.get(0));
        for (int i = 0; i < randomCustomList.size(); i++) {
            System.out.println(randomCustomList.get(i));
        }

        System.out.println();
        System.out.println("3.6 compary performance of array list vs linkedlist using get and add methods");

        PerformanceTester.compareArrayListAndLinkedList(200000);
//
//        3.6 Vergelijk performantie array list vs linkedlist door gebruik te maken van get en add methods
//        Adding 200000 to ArrayList: 1927 ms
//        Adding 200000 to LinkedList: 227 ms
//        Getting 200000 from ArrayList: 0 ms
//        Getting 200000 from LinkedList: 16 ms

        System.out.println();
        System.out.println("4.3 Test selectionSort algo vanuit de main door een List van 30 random basisobjecten " +
                "te sorteren en af te drukken.");

        Kollections.selectionSort(randomCustomList);
        for (int i = 0; i < randomCustomList.size(); i++) {
            System.out.println(randomCustomList.get(i));
        }


        System.out.println();
        System.out.println("4.5 Test MergeSort algo vanuit de main door een List van 30 random basisobjecten ");

        //Create a random list for  testing
        List<Klant> randomCustomListForMergeSort = PerformanceTester.randomList(5);

        Kollections.mergeSort(randomCustomListForMergeSort);
        for (int i = 0; i < randomCustomListForMergeSort.size(); i++) {
            System.out.println(randomCustomListForMergeSort.get(i));
        }
// 4.7 Test testSelectionsort en testMergeSort algos

        PerformanceTester.testSelectionSort();
        PerformanceTester.testMergeSort();

// 4.9 De Kollections::quickSort methode heb je cadeau gekregen.
//        Test vanuit de main door een List van 30 random basisobjecten te sorteren en af te drukken

        //Create a random list for  testing
        List<Klant> randomCustomListForQuickSort = PerformanceTester.randomList(30);


        System.out.println();
        System.out.println("4.9 Quick sort");
        System.out.println("Quick sort : before sort");
        for (int i = 0; i < randomCustomListForQuickSort.size(); i++) {
            System.out.println(randomCustomListForQuickSort.get(i));
        }

        Kollections.quickSort(randomCustomListForQuickSort);

        System.out.println();
        System.out.println();
        System.out.println("Quick sort : after sort");
        //System.out.println(randomCustomList.get(0));
        for (int i = 0; i < randomCustomListForQuickSort.size(); i++) {
            System.out.println(randomCustomListForQuickSort.get(i));
        }

        System.out.println();
        System.out.println("4.11 Testen van lineaire vs binary linear search");

        //Nieuwe lege klant toevoegen
        Klant klantTeZoeken = KlantFactory.newEmptyKlant();
        Klant nonExistent = KlantFactory.newEmptyKlant();
        klantTeZoeken.setAchternaam("zzzzz");
        nonExistent.setAchternaam("nonexistent");
        randomCustomListForQuickSort.add(klantTeZoeken);

        //QuickSort lijst
        Kollections.quickSort(randomCustomListForQuickSort);

        //Print
        System.out.println();
        System.out.println("De toegevoegde klant begint met de letters A en werd toegevoegd en gesorteerd");
        //System.out.println(randomCustomList.get(0));
        for (int i = 0; i < randomCustomListForQuickSort.size(); i++) {
            System.out.println(randomCustomListForQuickSort.get(i));
        }

        System.out.println("Index van klant met naam " + klantTeZoeken.getAchternaam() + ": " + Kollections.lineairSearch(randomCustomListForQuickSort, klantTeZoeken));
        System.out.println("Index van klant met naam " + klantTeZoeken.getAchternaam() + ": " + Kollections.binarySearch(randomCustomListForQuickSort, klantTeZoeken));
        System.out.println("Index van klant met naam " + klantTeZoeken.getAchternaam() + ": " + Kollections.lineairSearch(randomCustomListForQuickSort, nonExistent));
        System.out.println("Index van klant met naam " + klantTeZoeken.getAchternaam() + ": " + Kollections.binarySearch(randomCustomListForQuickSort, nonExistent));


        //5.8  In de klasse PerformanceTester schrijf je een nieuwe methode compareListMapToHasMap:

//        PerformanceTester.compareArraySetToTreeSet(1000);
        PerformanceTester.compareListMapToHashMap(1000);
    }
}



