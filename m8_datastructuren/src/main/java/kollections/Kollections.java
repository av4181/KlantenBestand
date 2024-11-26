package kollections;


public class Kollections {

    private Kollections() {
    }

    public static <T extends Comparable<T>> void selectionSort(List<T> list) {
        //Opdracht 4.2: use the selectionSort from the introduction module and make it generic!
        for (int i = 0; i < list.size() - 1; i++) {
            int indexSmallest = i;
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j).compareTo(list.get(indexSmallest)) < 0) {
                    indexSmallest = j;
                }
            }
            T tmp = list.get(i);
            list.set(i, list.get(indexSmallest));
            list.set(indexSmallest, tmp);
        }
    }

    // Opdracht 4.4: Ga naar de klasse kollections.  Kollections en implementeer daar de methode mergeSort.
    // Deze methode is overloaded. De tweede versie is bedoeld om recursief gebruikt te worden.
    // De merge methode is al gegeven; bestudeer de code!
    public static <T extends Comparable<T>> void mergeSort(List<T> list) {
        //splits in 2 delen, sorteer elk deel en merge dan

        mergeSort(list, 0, list.size() - 1);
    }

    //from inclusive, to inclusive
    // Opdracht 4.4: complete the algorithm! Use recursive calls and the merge method...
    private static <T extends Comparable<T>> void mergeSort(List<T> list, int from, int to) {
        //splits in 2 delen, sorteer elk deel en merge dan
        if (from > to) throw new IllegalArgumentException("from should be before to");
        if (to - from == 0) return;
        int middle = from + (to - from) / 2;
        mergeSort(list, from, middle);
        mergeSort(list, middle + 1, to);
        merge(list, from, to);
    }

    //from and to inclusive
    private static <T extends Comparable<T>> void merge(List<T> list, int from, int to) {
        List<T> mergedList = new ArrayList<>();
        int startSecondList = from + (to - from) / 2 + 1;
        int index1 = from;
        int index2 = startSecondList;
        while (index1 < startSecondList && index2 <= to) {
            if (list.get(index1).compareTo(list.get(index2)) < 0) {
                mergedList.add(list.get(index1++));
            } else {
                mergedList.add(list.get(index2++));
            }
        }
        if (index1 == startSecondList) {//copy rest of second list
            while (index2 <= to) {
                mergedList.add(list.get(index2++));
            }
        } else {//copy rest of first list
            while (index1 < startSecondList) {
                mergedList.add(list.get(index1++));
            }
        }
        for (int i = from; i <= to; i++) {
            list.set(i, mergedList.get(i - from));
        }
    }

    public static <T extends Comparable<T>> void quickSort(List<T> list) {
        quickSort(list, 0, list.size());
    }

    private static <T extends Comparable<T>> void quickSort(List<T> list, int start, int end) {
        if (end - start <= 0) return;
        int i = start;
        int j = end - 1;
        boolean movingI = true;
        while (i < j) {
            if (list.get(i).compareTo(list.get(j)) > 0) {
                //swap(list, i, j);
                T tmp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, tmp);
                movingI = !movingI;
            } else {
                if (movingI) {
                    i++;
                } else {
                    j--;
                }
            }
        }
        quickSort(list, start, i);
        quickSort(list, i + 1, end);
    }

    public static <T> int lineairSearch(List<T> list, T element) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(element)) return i;
        }
        return -1;
    }

    public static <T extends Comparable<T>> int binarySearch(List<T> sortedList, T element) {
        return binarySearch(sortedList, element, 0, sortedList.size());
    }

    private static <T extends Comparable<T>> int binarySearch(List<T> sortedList, T element, int from, int to) {
        // Implementeer deze methode
        if (from > to) {
            return -1;
        }
        int middle = (from + to) / 2;
        if (sortedList.get(middle).compareTo(element) == 0) {
            return middle;
        } else if (sortedList.get(middle).compareTo(element) < 0) {
            return binarySearch(sortedList, element, middle + 1, to);
        } else {
            return binarySearch(sortedList, element, from, middle - 1);
        }
    }
}
