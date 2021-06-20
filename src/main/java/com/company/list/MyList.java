package com.company.list;

public interface MyList<E> {
    void addItem(E e);
    void addItemByIndex(int n, E e);
    int size();
    void remove(E e);
    Object get(int n);
    void removeByIndex(int n);
    int indexOf(E element);
    default void validationListPosition(int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Unsupported list position.");
        }
    }
    default void validationListIsEmpty() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty.");
        }
    }
    default boolean isEmpty() {
        return this.size() == 0;
    }
}
