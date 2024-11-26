package kollections.maps;

import kollections.Collection;
import kollections.sets.Set;

// Opdracht 5.5
public interface Map<K, V> {
    void put(K key, V value);
    V get(K key);
    Collection<V> values();
    Set<K> keySet();
    int size();
}
