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
        int resultIndexOf = indexOf(elementData);
        if (resultIndexOf != -1) {
            removeByIndex(resultIndexOf);
        }
    }

    @Override
    public int indexOf(E elementData) {
        for (int i = 0; i < size(); i++) {
            if (item[i] == null && elementData == null) {
                return i;
            }
            if (item[i] != null && item[i].equals(elementData)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Object get(int index) {
        return item [index];
    }

    @Override
    public void removeByIndex (int index) {
        System.arraycopy(item, index+1, item, index, item.length-index-1);
        count--;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[");
        for (int i = 0; i < size(); i++) {
            if (i != (size()-1)){
                s.append(item[i]).append(", ");
            }
            else {
                s.append(item[i]);
            }
        }
        s.append("]");

        return s.toString();
    }
}
