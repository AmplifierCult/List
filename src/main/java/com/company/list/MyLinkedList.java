package com.company.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements Iterable<E>, MyList<E> {
    private Node first;
    private Node last;

    /**
     * Текущий размер списка.
     */
    private int size;

    @Override
    public void addItem(E e){
        // FIXME Почему нельзя добавить null?
        if (e == null) {
            throw new NullPointerException("This item is null.");
        }
        Node item = new Node(e);

        if (size != 0) { // Добавление второго и последующих элементов в список.
            last.next = item;
            item.previous = last;
            last = item;
            item.next = null;
        } else { // Добавление первого элемента в список.
            first = item;
            last = item;
        }

        size++;

    }

    @Override
    public E get(int n) {
        // TODO Реализовать! Проход с помощью while с счетчиком.
        if (n < 0 || n > size-1) {
            throw new IndexOutOfBoundsException("Unsupported list position.");
        }

        Node current = first;
        int currentIteration = 0;
        while (currentIteration <= n) {
            if(currentIteration == n) {
                return current.item;
            }

            currentIteration++;
            current = current.next;
        }

        return null;
    }

    @Override
    public void removeByIndex (int n) {
        // TODO Реализовать.
        throw new UnsupportedOperationException();
    }

    @Override
    public void addItem(int n, E e) {
        // TODO Реализовать. Получить по индексу, затем вставить новый элемент.
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void remove (E e) {
        if (size == 0) {
            throw new IllegalStateException("Cannot remove from and empty list.");
        }

        Node prev = first;
        Node curr = first;
        while (curr.next != null || curr == last) { // TODO Попробовать убрать curr == lats. Кажется не нужным условием.
            if (curr.item.equals(e)) { // FIXME Если список будет поддерживать null в себе, то тут может быть ошибка.
                // remove the last remaining element
                if (size == 1) {
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
                else {
                    prev.next = curr.next;
                }
                size--;
                break;
            }
            prev = curr;
            curr = prev.next;

        }
    }

    /**
     * Описание элемента списка.
     */
    private class Node {
        private final E item;
        private Node next;
        private Node previous;

        public Node(E item) {
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

    // TODO equals and hashcode.

    @Override
    public String toString() {
        // TODO Сделать с другим паттерном. Обрамление - [], разделитель элементов - ','
        StringBuilder s = new StringBuilder();
        for (E item : this) // TODO Через while.
            s.append(item).append(" ");
        return s.toString();
    }

}
