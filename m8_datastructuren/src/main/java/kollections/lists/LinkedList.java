package kollections.lists;

import kollections.Kollections;


/*
LinkedList is een ketting van elementen waarbij het ene element het adres van het volgende element bevat enz.
 */
public class LinkedList<E> implements List<E> {
    static class Node<V> {
        V value;
        Node<V> next;

        public Node(V value) {
            this.value = value;
        }
    }

    private Node<E> root;
    private int size;

    public LinkedList() {
    }

    @Override
    public void add(int index, E element) {
        if (index > this.size || index < 0) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
        Node<E> newNode = new Node<>(element);
        if (index == 0) {
            newNode.next = root;
            root = newNode;
        } else {
            Node<E> node = root;
            for (int i = 0; i < index - 1; i++) {
                node = node.next;
            }
            newNode.next = node.next;
            node.next = newNode;
        }
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
        Node<E> node = root;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        node.value = element;
    }

    @Override
    public int size() {
        return size;
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

    /*
    Worst case Tijdscomplexiteit O(n) slechter dan een ArrayList dus !
     */
    @Override
    public E remove(int index) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
        if (index == 0) {
            E oldElement = root.value;
            root = root.next;
            size--;
            return oldElement;
        } else {
            Node<E> beforeNode = root;
            for (int i = 1; i < index; i++) {
                beforeNode = beforeNode.next;
            }
            E oldElement = beforeNode.next.value;
            beforeNode.next = beforeNode.next.next;
            size--;
            return oldElement;
        }
    }

    /*
    Worst case Tijdscomplexiteit O(n) slechter dan een ArrayList dus !
     */
    @Override
    public E get(int index) {
        //Implementeer deze methode
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
        Node<E> node = root;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.value;
    }

    // Opdracht 5.4: Maak in de indexOf-methode gebruik van Kollections linearSearch
    @Override
    public int indexOf(E element) {
        return Kollections.lineairSearch(this, element);
    }
}
