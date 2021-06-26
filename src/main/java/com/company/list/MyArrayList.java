package com.company.list;

public class MyArrayList<E> implements MyList<E> {

    private Object [] item;
    private int size;

    public MyArrayList() {
       this(0);
    }

    public MyArrayList(int capacity) {
        validationCapacity(capacity);
        this.item = new Object[capacity];
    }

    private void increaseSize() {
        Object [] temp = item;
        item = new Object[temp.length * 2 + 1];
        System.arraycopy(temp, 0, item, 0, temp.length);
    }

    private void validationCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        }
    }

    @Override
    public void addItem(E elementData) {
        if (item.length == size()){
            increaseSize();
        }
        item[size()] = elementData;
        size++;
    }

    @Override
    public void addItemByIndex(int index, E elementData) {
        validationListPosition(index);
        if (item.length == size()){
            increaseSize();
        }
        System.arraycopy(item, index, item, index+1, item.length-index-1);
        item[index] = elementData;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void remove(E elementData) {
        validationListIsEmpty();
        int resultIndexOf = indexOf(elementData);
        validationListPosition(resultIndexOf);
        removeByIndex(resultIndexOf);
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
        validationListIsEmpty();
        validationListPosition(index);
        return item [index];
    }

    @Override
    public void removeByIndex (int index) {
        validationListIsEmpty();
        validationListPosition(index);
        System.arraycopy(item, index+1, item, index, item.length-index-1);
        size--;
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
