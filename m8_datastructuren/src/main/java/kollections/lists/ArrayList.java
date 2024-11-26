package kollections.lists;

import kollections.Kollections;

import java.util.Arrays;

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
//        Object[] newElements = new Object[elements.length*2];
//        for (int i = 0; i < elements.length; i++) {
//            newElements[i] = elements[i];
//        }
//        System.arraycopy(elements, 0, newElements, 0, elements.length);
//        elements = newElements;

        //alternative
        // Double the size of the array
        elements = Arrays.copyOf(elements, 2*elements.length);
    }

    @Override
    public void add(int index, E element) {
        if (index > this.size || index < 0) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
        if (size == elements.length) {
            expand();
        }
//        for (int i = size; i > index; i--) {
//            elements[i] = elements[i - 1];
//        }
        if (size - index >= 0) System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    @Override
    public void add(E element) {
        add(size, element);
    }

    // Opdracht 5.4: Pas nu de List-implementaties ArrayList en LinkedList aan, werk alle ontbrekende methoden correct uit.
    @Override
    public boolean remove(E element) {
        int index = Kollections.lineairSearch(this, element);
        if (index != -1){
            remove(index);
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != 1;
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
//        for (int i = index; i < size - 1; i++) {
//            elements[i] = elements[i + 1];
//        }
        if (size - 1 - index >= 0) System.arraycopy(elements, index + 1, elements, index, size - 1 - index);
        size--;
        return oldValue;
    }

    // Opdracht 5.4: Maak in de indexOf-methode gebruik van Kollections linearSearch
    @Override
    public int indexOf(E element) {
        return Kollections.lineairSearch(this, element);
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

