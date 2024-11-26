package kollections.sets;

import kollections.Collection;
import kollections.lists.List;

public interface Set<E> extends Collection<E> {
    List<E> toList();
}

