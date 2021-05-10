package com.company.list;

import static org.junit.Assert.*;

public class MyArrayListTest {

    @org.junit.Test
    public void addItem() {
        MyLinkedList <Object> list = new MyLinkedList<>();
        list.addItem("first");
        list.addItem("second");
        list.addItem(3);
        list.addItem("fourth");
        list.addItem(5);
        list.addItem("sixth");
        list.addItem("seventh");
        int actual = list.size();
        int expected = 7;
        assertEquals(expected, actual);
    }

    @org.junit.Test
    public void size() {
    }

    @org.junit.Test
    public void remove() {
    }

    @org.junit.Test
    public void get() {
    }

    @org.junit.Test
    public void removeByIndex() {
    }
}