package com.company;

public interface MyListInterface<E> {
    void addItem(E e);
    void addItem(int n, E e);
    int size();
    void remove (E e);
    E get (int n);
}
