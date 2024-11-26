package kollections.lists;

import kollections.Collection;

// Opdracht 5.3: Aanpassingen aan de List interface: overerven van Collection en de indexOf methode toevoegen

public interface List<E> extends Collection<E>{
    void add(int index, E element);
    void add(E element);
    void set(int index, E element);
    int size();
    E remove(int index);
    int indexOf(E element);

    E get(int index);
}
