package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements Iterable<E> {
    private Node first;
    private Node last;
    private int pointer;


    public MyLinkedList() {
        first = null;
        last = null;
        pointer = 0;
    }

    public void addItem (E e){
        if (e == null) {
            throw new NullPointerException("This item is null.");
        }
        Node item = new Node(e);

        if (pointer != 0){
            last.next = item;
            item.previous = last;
            last = item;
            item.next = null;
        }
        else {
            first = item;
            last = item;
        }
        pointer++;
    }
    public int size() {
        return pointer;
    }

    public void remove (E e) {
        if (pointer == 0) {
            throw new IllegalStateException("Cannot remove from and empty list.");
        }
        Node prev = first;
        Node curr = first;
        while (curr.next != null || curr == last) {
            if (curr.item.equals(e)) {
                // remove the last remaining element
                if (pointer == 1) {
                    first = null; last = null;
                }
                // remove first element
                else if (curr.equals(first)) {
                    first = first.next;
                }
                // remove last element
                else if (curr.equals(last)) {
                    last = prev; last.next = null;
                }
                // remove element
                else { prev.next = curr.next; }
                pointer--;
                break;
            }
            prev = curr;
            curr = prev.next;
        }
    }



    private class Node {
        private Node previous;
        private Node next;
        private final E item;

        public Node(E item) {
            previous = null;
            next = null;
            this.item = item;
        }

    }

    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<E> {
        private Node current = first;


        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E item = current.item;
            current = current.next;
            return item;
        }

        public boolean hasNext() { return current != null; }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    @Override public String toString() {
        StringBuilder s = new StringBuilder();
        for (E item : this)
            s.append(item).append(" ");
        return s.toString();
    }

}
