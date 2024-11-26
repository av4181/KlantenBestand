package kollections;

public interface List<E> {
    void add(int index, E element);
    void add(E element);
    void set(int index, E element);
    int size();
    E remove(int index);
    E get(int index);
}
