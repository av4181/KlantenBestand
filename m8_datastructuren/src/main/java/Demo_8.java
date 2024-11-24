import kollections.ArrayList;
import kollections.Kollections;
import model.Klant;
import model.KlantFactory;
import model.KlantType;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Demo_8 {

    public static void main(String[] args) {

        Klant emptyKlant = KlantFactory.newEmptyKlant();
        System.out.println("Empty klant: " + emptyKlant);

        Klant filledKlant = KlantFactory.newFilledKlant(1, "Jan", "Janssens", "jan.janssens@example.com", KlantType.PARTICULIER, 0.21, LocalDate.of(1990, 5, 10), false);
        System.out.println("Filled klant: " + filledKlant);

        List<Klant> randomKlanten = Stream.generate(KlantFactory::newRandomKlant)
                .limit(30)
                .sorted(Comparator.comparing(Klant::getAchternaam))
                .collect(Collectors.toList());
        System.out.println("\n30 random klanten gesorteerd op achternaam:");
        randomKlanten.forEach(System.out::println);
    }

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
}



