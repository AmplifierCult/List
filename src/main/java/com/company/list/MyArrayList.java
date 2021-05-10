package com.company.list;

public class MyArrayList<E> implements MyListInterface<E> {

    E[] item;



    public MyArrayList() {
        this.item = (E[]) new Object[0];

    }

    @Override
    public void addItem(E e) {
        try {
            E[] temp = item;
            item = (E[]) new Object[temp.length * 2 + 1];
            System.arraycopy(temp, 0, item, 0, temp.length);
            item[item.length - 1] = e;
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void addItem(int n, E e) {
        item[n] = e;
    }

    @Override
    public int size() {
        return item.length;
    }

    @Override
    public void remove(E e) {

    }

    @Override
    public E get(int n) {
        return item [n];
    }

    @Override
    public void removeByIndex (int n) {
        item[n] = null;
    }
}
