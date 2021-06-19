package com.company.list;

public interface MyList<E> {
    void addItem(E e);
    void addItemByIndex(int n, E e);
    int size();
    void remove(E e);
    Object get(int n);
    void removeByIndex(int n);
    int indexOf(E element);
}
