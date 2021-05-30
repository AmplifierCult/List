package com.company.list;

import java.util.*;

public class MyLinkedList<E> implements Iterable<E>, MyList<E> {

    /**
     * Первый элемент списка.
     */
    private Node first;

    /**
     * Последний элемент списка.
     */
    private Node last;

    /**
     * Текущий размер списка.
     */
    private int size;

    /**
     * Метод addItem добавляет последний элемент списка.
     */
    @Override
    public void addItem(E e) {
        Node newItem = new Node(e);

        //Добавление второго и последующих элементов в список.
        if (size != 0) {
            last.next = newItem;
            newItem.previous = last;
            last = newItem;
            newItem.next = null;

            //Добавление первого элемента в список.
        } else {
            first = newItem;
            last = newItem;
        }
        size++;

    }

    /**
     * Метод get возвращает элемент списка по индексу.
     */
    @Override
    public E get(int n) {
        if (n < 0 || n > size - 1) {
            throw new IndexOutOfBoundsException("Unsupported list position.");
        }

        Node curr = first;
        int currentIteration = 0;
        while (currentIteration <= n) {
            if (currentIteration == n) {
                return curr.item;
            }

            currentIteration++;
            curr = curr.next;
        }

        return null;
    }

    /**
     * Метод removeByIndex удаляет элемент списка по индексу.
     */
    @Override
    public void removeByIndex(int n) {
        if (size == 0) {
            throw new IllegalStateException("List is empty");
        }

        if (n < 0 || n > size) {
            throw new IndexOutOfBoundsException("Unsupported list position.");
        }

        Node curr = first;
        Node prev = first;
        int currentIteration = 0;
        while (currentIteration <= n) {
            if (currentIteration == n) {
                if (size == 1) {
                    first = null;
                    last = null;
                }

                //remove first element
                else if (curr.equals(first)) {
                    first = first.next;
                }

                //remove last element
                else if (curr.equals(last)) {
                    last = prev;
                    last.next = null;
                }

                //remove element
                else {
                    prev.next = curr.next;
                }
                size--;
                break;
            }
            currentIteration++;
            prev = curr;
            curr = prev.next;
        }
    }

    /**
     * Метод добавляет следующий элемент списка по индексу.
     */
    @Override
    public void addItemByIndex(int n, E e) {
        //TODO Реализовать. Получить по индексу, затем вставить новый элемент.

        if (n < 0 || n > size) {
            throw new IndexOutOfBoundsException("Unsupported list position.");
        }
        Node newItem = new Node(e);
        Node curr = first;
        Node prev = first;
        int currentIteration = 0;
        while (currentIteration <= n) {
            if (currentIteration == n) {

                if (size == 0) { // TODO некорректная работа, написать тест.
                    first = newItem;
                    last = newItem;
                }

                //add first element
                else if (n == 0) {
                    first = newItem;
                    curr.previous = first;
                }

                //add last element
                else if (n == size) {
                    last = newItem;
                    prev.next = newItem;
                    newItem.previous = prev;
                }

                //add element
                else {
                    prev.next = newItem;
                    newItem.previous = prev;
                    curr.previous = newItem;
                    newItem.next = curr;
                }
                size++;
                break;
            }
            currentIteration++;
            prev = curr;
            curr = prev.next;
        }
    }

    @Override
    public int indexOf(E element) {
        int index = 0;
        Node curr = first;
        while (curr != null) {

            if (curr.item == null && element == null) {
                return index;
            }

            if (curr.item != null && curr.item.equals(element)) {
                return index;
            }
            index++;
            curr = curr.next;
        }
        return -1;
    }

    /**
     * Метод size возвращает текущий размер списка.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Метод remove удаляет элемент по содержимому.
     */
    @Override
    public void remove(E e) {
        if (size == 0) {
            throw new IllegalStateException("Cannot remove from and empty list.");
        }

        Node prev = first;
        Node curr = first;
        // В чем суть curr == last
        while (curr.next != null || curr == last) { //TODO Попробовать убрать curr == last. Кажется не нужным условием.
            if (curr.item == null || curr.item.equals(e)) { //FIXME Если список будет поддерживать null в себе, то тут может быть ошибка.
                //null equals(null) возвращает NullPointerException

                //remove the last remaining element
                if (size == 1) {
                    first = null;
                    last = null;
                }

                //remove first element
                else if (first.item == null || curr.equals(first)) {
                    first = first.next;
                }

                //remove last element
                else if (last.item == null || curr.equals(last)) {
                    last = prev;
                    last.next = null;
                }

                //remove element
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

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof List))
            return false;

        Iterator<E> e1 = this.iterator();
        Iterator<?> e2 = ((MyLinkedList<?>) o).iterator();
        while (e1.hasNext() && e2.hasNext()) {
            E o1 = e1.next();
            Object o2 = e2.next();
            if (!(Objects.equals(o1, o2)))
                return false;
        }
        return !(e1.hasNext() || e2.hasNext());
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        for (E e : this)
            hashCode = 31*hashCode + (e==null ? 0 : e.hashCode());
        return hashCode;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        Node curr = first;
        Node prev;
        s.append("[");
        int currentIteration = 1;
        while (currentIteration <= size) {
            if (curr != last){
                s.append(curr.item).append(", ");
            }
            else {
                s.append(curr.item);
            }
            currentIteration++;
            prev = curr;
            curr = prev.next;
        }
        s.append("]");

        return s.toString();
    }
}
