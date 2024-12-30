package generics;

public interface FIFOQueue<T> {
    // Voeg een element toe aan de queue met een bepaalde prioriteit.
    boolean enqueue(T element, int priority);

    // Verwijder en return het element dat het langst in de queue staat i.e. met de hoogste prioriteit
    T dequeue();

    // Zoek naar een element in de queue en return de positie
    int search(T element);

    // Return het aantal elementen in de queue
    int getSize();
}
