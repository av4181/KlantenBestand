package kollections.maps;

import kollections.sets.ArraySet;
import kollections.sets.Set;
import kollections.lists.List;
import kollections.lists.ArrayList;
import kollections.Collection;

public class ListMap<K,V> implements Map<K, V> {
    private List<K> keys = new ArrayList<>();
    private List<V> values = new ArrayList<>();
    private int size;

    @Override
    public void put(K key, V value) {
        int index = keys.indexOf(key);
        if (index!=-1) {
            values.set(index, value);
        } else {
            keys.add(key);
            values.add(value);
            size++;
        }
    }

    @Override
    public V get(K key) {
        int index = keys.indexOf(key);
        if (index!=-1) {
            return values.get(index);
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Collection<V> values() {
        return values;
    }

    @Override
    public Set<K> keySet() {
        ArraySet<K> keySet = new ArraySet<>();
        for (int i=0;i<keys.size();i++) {
            keySet.add(keys.get(i));
        }
        return keySet;
    }
}

