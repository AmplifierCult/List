package com.company.list;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyArrayListTest {

    @Test
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

    @Test
    public void size() {
    }

    @Test
    public void remove() {
    }

    @Test
    public void get() {
    }

    @Test
    public void removeByIndex() {
    }
}