package com.company.list;

public class MyLinkedListTests extends BaseListTests {
    @Override
    protected <E> MyList<E> getEmptyList() {
        return new MyLinkedList<>();
    }
}
