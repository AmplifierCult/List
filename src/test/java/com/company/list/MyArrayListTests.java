package com.company.list;

public class MyArrayListTests extends BaseListTests {
    @Override
    protected <E> MyList<E> getEmptyList() {
        return new MyArrayList<E>();
    }
}
