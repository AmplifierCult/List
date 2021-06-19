package com.company.list;

public class MyArrayList<E> implements MyList<E> {

    Object [] item;
    int count;

    public MyArrayList() {
        this.item = new Object[0];
    }

    public MyArrayList(int capacity) {
        if (capacity >= 0 && capacity < Integer.MAX_VALUE) {
            this.item = new Object[capacity];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        }
    }

    @Override
    public void addItem(E elementData) {
        Object [] temp = item;
        if (item.length == size()){
            item = new Object[temp.length * 2 + 1];
            System.arraycopy(temp, 0, item, 0, temp.length);
        }
        item[size()] = elementData;
        count++;
    }

    @Override
    public void addItemByIndex(int index, E elementData) {
        // TODO перетирается элемент
        item[index] = elementData;
        count++;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public void remove(E elementData) {
        //TODO
        count--;
    }

    @Override
    public int indexOf(E elementData) {
        //TODO Реализовать!
        return 0;
    }

    @Override
    public Object get(int index) {
        return item [index];
    }

    @Override
    public void removeByIndex (int index) {
        //TODO остается дырка в виде null.
        item[index] = null;
        count--;
    }
}
