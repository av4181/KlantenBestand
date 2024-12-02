package kollections.sets;

import kollections.Collection;
import kollections.lists.List;

// Opdracht 5.5
public interface Set<E> extends Collection<E> {
    List<E> toList();
}

