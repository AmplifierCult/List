package com.company.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

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
    public void addItem(E e){
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
        if (n < 0 || n > size-1) {
            throw new IndexOutOfBoundsException("Unsupported list position.");
        }

        Node curr = first;
        int currentIteration = 0;
        while (currentIteration <= n) {
            if(currentIteration == n) {
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
    public void removeByIndex (int n) {
        if (n < 0 || n > size-1) {
            throw new IndexOutOfBoundsException("Unsupported list position.");
        }
        Node curr = first;
        Node prev = first;
        int currentIteration = 0;
        while (currentIteration <= n) {
            if(currentIteration == n) {
                if (size == 1) {
                    first = null; last = null;
                }

                //remove first element
                else if (curr.equals(first)) {
                    first = first.next;
                }

                //remove last element
                else if (curr.equals(last)) {
                    last = prev; last.next = null;
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

        if (n < 0 || n > size-1) {
            throw new IndexOutOfBoundsException("Unsupported list position.");
        }
        Node newItem = new Node(e);
        Node curr = first;
        Node prev = first;
        int currentIteration = 0;
        while (currentIteration <= n) {
            if(currentIteration == n) {
                if (size == 1) { // TODO некорректная работа, написать тест.
                    first = newItem; last = newItem;
                }

                //add first element
                else if (n == 0) {
                    first = newItem;
                    curr.previous = first;
                }

                //add last element
                else if (n == size-1) {
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
        //TODO реализовать!
        return 0;
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
        while (curr.next != null || curr == last) { //TODO Попробовать убрать curr == last. Кажется не нужным условием.
            //Нельзя убрать curr == last т.к. список может состоять полностью из null или иметь подряд несколько элементов null.
            if (curr.item == null || curr.item.equals(e)) { //FIXME Если список будет поддерживать null в себе, то тут может быть ошибка.
                //null equals(null) возвращает NullPointerException

                //remove the last remaining element
                if (size == 1) {
                    first = null; last = null;
                }

                //remove first element
                else if (first.item == null || curr.equals(first)) {
                    first = first.next;
                }

                //remove last element
                else if (last.item == null || curr.equals(last)) {
                    last = prev; last.next = null;
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

        public boolean hasNext() { return current != null; }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    //TODO equals and hashcode.

    @Override
    public boolean equals(Object o) { // FIXME Не подходит, требуется проходить по всем элементам.
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyLinkedList<?> that = (MyLinkedList<?>) o;
        return size == that.size && Objects.equals(first, that.first) && Objects.equals(last, that.last);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, last, size); // FIXME Не подходит, не учитывает большинство элементов в списке.
    }

    @Override
    public String toString() {
        //TODO Сделать с другим паттерном. Обрамление - [], разделитель элементов - ','
        StringBuilder s = new StringBuilder();
        for (E item : this) // TODO Через while.
            s.append(item).append(" ");
        return s.toString();

    }

}
