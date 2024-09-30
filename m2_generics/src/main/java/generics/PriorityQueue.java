package generics;

import java.util.*;

/**
 * De klasse PriorityQueue is een op maat gemaakte implementatie van de Java Util versie.
 * Deze klasse kan elementen volgens het FIFO principe in de queueu zetten alsook terug uit de queue halen.
 *
 * @param <T> het element in de wachtrij
 * @author Andreas Van Loon
 * @version 1.0
 */

public class PriorityQueue<T> implements FIFOQueue<T> {
        private TreeMap<Integer, LinkedList<T>> linkedListTreeMap = new TreeMap<>(Comparator.reverseOrder());

    /**
     * Het element en de prioriteit komen als parameters binnen.
     * Onderzoek eerst of het element nog niet voorkomt in de map (ongeacht de prioriteit).
     * Indien wel, dan retourneer je false. Indien niet, dan kijk je of er al een entry bestaat van deze prioriteit.
     * Als dat het geval is, dan voeg je het nieuwe element achteraan toe in deze prioriteitsreeks,
     * anders maak je een nieuwe List met daarin dit nieuwe element.
     * @param element  het element dat in de wachtrij gezet dient te worden
     * @param priority de prioriteit waarmee het element in de wachtrij dient gezet te worden
     * @return true als het element nog niet bestond en het succesvol is toegevoegd aan de wachtrij
     */
    @Override
    public boolean enqueue(T element, int priority) {
        for (LinkedList<T> value : linkedListTreeMap.values()) {
            for (T t : value) {
                if (t == element) return false;
            }
        }

        if (linkedListTreeMap.containsKey(priority)){
            linkedListTreeMap.get(priority).addLast(element);
        }else {
            LinkedList<T> newLinkedlist = new LinkedList<T>();
            newLinkedlist.addLast(element);
            linkedListTreeMap.put(priority, newLinkedlist);
        }
        return true;
    }
    /**
     * Retourneer het element dat zich vooraan bevindt in de hoogste prioriteitsreeks en verwijder het uit de queue.
     *
     * @return het element vooraan in de hoogste prioriteitsreeks, indien er geen element(en) bestaat null
     */
    @Override
    public T dequeue() {
        T removeLast;
        if (linkedListTreeMap.firstEntry().getValue().isEmpty()){
            linkedListTreeMap.remove(linkedListTreeMap.firstKey());
            removeLast= linkedListTreeMap.get(linkedListTreeMap.firstKey()).removeLast();
        } else removeLast= linkedListTreeMap.get(linkedListTreeMap.firstKey()).removeLast();


        return removeLast;
    }

    /**
     * Zoek het element op in de map. Indien gevonden, dan retourneer je de positie in de volledige queue
     * (dus niet de positie in de LinkedList).
     * Indien niet gevonden: -1
     *
     * @param element het te zoeken element
     * @return het element indien dit gevonden is in de wachtrij, anders -1
     */
    @Override
    public int search(T element) {
        List<T> elements = new ArrayList<>();
        for (LinkedList<T> ts : linkedListTreeMap.values()) {
            elements.addAll(ts);
        }

        if (elements.contains(element)) {
            return elements.indexOf(element) + 1;
        } else {
            return -1;
        }
    }

    /**
     * @return het totaal aantal elementen in de volledige queue.
     */
    @Override
    public int getSize() {
        int sum = 0;
        for (LinkedList<T> tLinkedList : linkedListTreeMap.values()) {
            sum += tLinkedList.size();
        }
        return sum;
    }

    /**
     * Genereert een overzicht van de PriorityQueue in de vorm van een string met op elke afzonderlijke regel:
     * het prioriteitsgetal, gevolgd door een : en daarachter het element zelf.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Integer, LinkedList<T>> integerLinkedListEntry : linkedListTreeMap.entrySet()) {
            for (T t : integerLinkedListEntry.getValue()) {
                stringBuilder.append(integerLinkedListEntry.getKey()).append(": ");
                stringBuilder.append(t);
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }
}
