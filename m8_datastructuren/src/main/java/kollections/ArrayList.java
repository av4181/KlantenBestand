package kollections;

import model.Klant;

public class ArrayList<E> implements List<E> {
    private static final int INITIAL_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[INITIAL_CAPACITY];
        size = 0;
    }

    private void expand() {
        //implement this method
        Object[] newElements = new Object[elements.length*2];
        System.arraycopy(elements, 0, newElements, 0, elements.length);
        elements = newElements;
    }

    @Override
    public void add(int index, E element) {
        if (index > this.size || index < 0) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
        //implement this method
        if (size == elements.length) {
            expand();
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    @Override
    public void add(E element) {
        add(size, element);
    }

    @Override
    public void set(int index, E element) {
        if (index > this.size || index < 0) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
        elements[index] = element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
        E oldValue = (E) elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        return oldValue;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
        return (E) elements[index];
    }
}

