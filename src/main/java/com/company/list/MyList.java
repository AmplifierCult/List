package com.company.list;

public interface MyList<E> {
    void addItem(E e);
    void addItem(int n, E e);
    int size();
    void remove (E e);
    E get (int n);
    void removeByIndex (int n);
}