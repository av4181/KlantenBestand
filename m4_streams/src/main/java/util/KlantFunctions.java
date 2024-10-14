package util;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

public class KlantFunctions {
    //Opdarcht 2.3
    public static <T> List<T> filteredList(List<T> klantList, Predicate<T> predicate) {
    //        List<T> gefilterdeLijst = new ArrayList<>();
    //        for (T klant : klantList){
    //            if (predicate.test(klant)) {
    //                gefilterdeLijst.add(klant);
    //            }
    //        }
    //        return gefilterdeLijst;
        //Opdracht 4.2
        return klantList.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
    //Opdracht 2.5
    public static <T> Double average(List<T> klantList, ToDoubleFunction<T> mapper) {
    //        double sum = 0;
    //        for (T klant : klantList) {
    //            sum += mapper.applyAsDouble(klant);
    //        }
    //        return sum / klantList.size();
        //Opdracht 4.2
        return klantList.stream()
                .mapToDouble(mapper)
                .average()
                .orElse(0.0);
    }
    //Opdracht 2.7
    public static <T> long countIf(List<T> klantList, Predicate<T> predicate) {
    //        long count = 0;
    //        for (T klant : klantList) {
    //            if (predicate.test(klant)) {
    //                count++;
    //            }
    //        }
    //        return count;
        //opdracht 4.2
        return klantList.stream()
                .filter(predicate)
                .count();
    }
}
