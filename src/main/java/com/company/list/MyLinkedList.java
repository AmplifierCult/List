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
    public void addItem(E elementData) {
        Node newItem = new Node(elementData);

        //Добавление второго и последующих элементов в список.
        if (!isEmpty()) {
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
    public Object get(int index) {
        validationListIsEmpty();
        validationListPosition(index);
        Node currentNode = first;
        int currentIteration = 0;
        while (currentIteration <= index) {
            if (currentIteration == index) {
                return currentNode.item;
            }
            currentIteration++;
            currentNode = currentNode.next;
        }
        return null;
    }

    /**
     * Метод removeByIndex удаляет элемент списка по индексу.
     */
    @Override
    public void removeByIndex(int index) {
        validationListIsEmpty();
        validationListPosition(index);
        Node currentNode = first;
        Node previousNode = first;
        int currentIteration = 0;
        while (currentIteration <= index) {
            if (currentIteration == index) {
                if (size() == 1) {
                    first = null;
                    last = null;
                }

                //remove first element
                else if (currentNode.equals(first)) {
                    first = first.next;
                }

                //remove last element
                else if (currentNode.equals(last)) {
                    last = previousNode;
                    last.next = null;
                }

                //remove element
                else {
                    previousNode.next = currentNode.next;
                }
                size--;
                break;
            }
            currentIteration++;
            previousNode = currentNode;
            currentNode = previousNode.next;
        }
    }

    /**
     * Метод добавляет элемент списка по индексу.
     */
    @Override
    public void addItemByIndex(int index, E elementData) {
        validationListPosition(index);
        Node newItem = new Node(elementData);
        Node currentNode = first;
        Node previousNode = first;
        int currentIteration = 0;
        while (currentIteration <= index) {
            if (currentIteration == index) {

                if (isEmpty()) {
                    first = newItem;
                    last = newItem;
                }

                //add first element
                else if (index == 0) {
                    first = newItem;
                    currentNode.previous = first;
                }

                //add last element
                else if (index == size()) {
                    last = newItem;
                    previousNode.next = newItem;
                    newItem.previous = previousNode;
                }

                //add element
                else {
                    previousNode.next = newItem;
                    newItem.previous = previousNode;
                    currentNode.previous = newItem;
                    newItem.next = currentNode;
                }
                size++;
                break;
            }
            currentIteration++;
            previousNode = currentNode;
            currentNode = previousNode.next;
        }
    }

    @Override
    public int indexOf(E elementData) {
        int index = 0;
        Node currentNode = first;
        while (currentNode != null) {

            if (currentNode.item == null && elementData == null) {
                return index;
            }

            if (currentNode.item != null && currentNode.item.equals(elementData)) {
                return index;
            }
            index++;
            currentNode = currentNode.next;
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
     * Метод remove удаляет элемент по содержимому. Если в списке несколько одинаковых элементов, то удаляет первый по счету.
     */
    @Override
    public void remove(E elementData) {
        validationListIsEmpty();
        Node previousNode = first;
        Node currentNode = first;
        while (currentNode.next != null || currentNode == last) {
            if (currentNode.item == null || currentNode.item.equals(elementData)) {

                //remove the last remaining element
                if (size() == 1) {
                    first = null;
                    last = null;
                }

                //remove first element
                else if (first.item == null || currentNode.equals(first)) {
                    first = first.next;
                }

                //remove last element
                else if (last.item == null || currentNode.equals(last)) {
                    last = previousNode;
                    last.next = null;
                }

                //remove element
                else {
                    previousNode.next = currentNode.next;
                }
                size--;
                break;
            }
            previousNode = currentNode;
            currentNode = previousNode.next;
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
        Node currentNode = first;
        Node previousNode;
        s.append("[");
        int currentIteration = 1;
        while (currentIteration <= size()) {
            if (currentNode != last){
                s.append(currentNode.item).append(", ");
            }
            else {
                s.append(currentNode.item);
            }
            currentIteration++;
            previousNode = currentNode;
            currentNode = previousNode.next;
        }
        s.append("]");

        return s.toString();
    }
}
