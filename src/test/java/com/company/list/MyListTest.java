package com.company.list;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyListTest {

    //TODO Тесты.
    // 1. toString list is empty -> []
    // 2. toString list with single item -> [item]
    // 3. toString list with several items -> [item1, item2, item3, ... item_n]


    @Test
    public void addItem() {
        MyLinkedList<Object> list = new MyLinkedList<>();
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

    @Test
    public void deleteItemFromListSizeOne() {
        MyList<Number> numberList = new MyLinkedList<>();
        numberList.addItem(1);

        numberList.remove(1);

        assertEquals(0, numberList.size());
    }

    @Test
    public void deleteLastItemFromListSizeTwo() {
        MyList<Number> numberList = new MyLinkedList<>();
        numberList.addItem(1);
        numberList.addItem(2);

        numberList.remove(2);

        assertEquals(1, numberList.size());
        assertEquals(1, numberList.get(0));
    }

    @Test
    public void deleteFirstItemFromListSizeTwo() {
        MyList<Number> numberList = new MyLinkedList<>();
        numberList.addItem(1);
        numberList.addItem(2);

        numberList.remove(1);

        assertEquals(1, numberList.size());
        assertEquals(2, numberList.get(0));
    }

    @Test
    public void indexOfWhenListIsEmpty() {
        //Given
        MyList<Number> numberList = new MyLinkedList<>();

        //When
        int actual = numberList.indexOf(0);

        //Then
        assertEquals(-1, actual);
    }

    @Test
    public void indexOfWhenFirstElement() {
        //Given
        MyList<Number> numberList = new MyLinkedList<>();
        numberList.addItem(1);
        numberList.addItem(2);
        numberList.addItem(3);

        //When
        int actual = numberList.indexOf(1);

        //Then
        assertEquals(0, actual);
    }

    @Test
    public void indexOfWhenFirstOfEqualitySequenceElements() {
        //Given
        MyList<Number> numberList = new MyLinkedList<>();
        numberList.addItem(1);
        numberList.addItem(2);
        numberList.addItem(2);
        numberList.addItem(2);
        numberList.addItem(3);

        //When
        int actual = numberList.indexOf(2);

        //Then
        assertEquals(1, actual);
    }

    @Test
    public void indexOfWhenLastElement() {
        //Given
        MyList<Number> numberList = new MyLinkedList<>();
        numberList.addItem(1);
        numberList.addItem(2);
        numberList.addItem(3);

        //When
        int actual = numberList.indexOf(3);

        //Then
        assertEquals(2, actual);
    }

    @Test
    public void indexOfWhenPreLastElement() {
        //Given
        MyList<Number> numberList = new MyLinkedList<>();
        numberList.addItem(1);
        numberList.addItem(2);
        numberList.addItem(32);
        numberList.addItem(3);

        //When
        int actual = numberList.indexOf(32);

        //Then
        assertEquals(2, actual);
    }

    @Test
    public void indexOfWhenOneElementNull() {
        //Given
        MyList<Number> numberList = new MyLinkedList<>();
        numberList.addItem(1);
        numberList.addItem(null);
        numberList.addItem(32);
        numberList.addItem(3);

        //When
        int actual = numberList.indexOf(null);

        //Then
        assertEquals(1, actual);
    }

    @Test
    public void indexOfWhenSearchInListWithNull() {

        //Given
        MyList<Number> numberList = new MyLinkedList<>();
        numberList.addItem(1);
        numberList.addItem(null);
        numberList.addItem(32);
        numberList.addItem(3);

        //When
        int actual = numberList.indexOf(3);

        //Then
        assertEquals(3, actual);
    }

    @Test
    public void indexOfSearchInListWithoutNull() {
        //Given
        MyList<Number> numberList = new MyLinkedList<>();
        numberList.addItem(1);
        numberList.addItem(45);
        numberList.addItem(32);
        numberList.addItem(3);

        //When
        int actual = numberList.indexOf(null);

        //Then
        assertEquals(-1, actual);
    }

    @Test
    public void addItemByIndexWhenListSizeOne() {
        //Given
        MyList<Number> numberList = new MyLinkedList<>();
        numberList.addItem(45);

        //When
        numberList.addItemByIndex(0,42);
        int actual = numberList.size();

        //Then
        assertEquals(2, actual);
    }

    @Test
    public void addItemByIndexWhenListEmpty() {
        //Given
        MyList<Number> numberList = new MyLinkedList<>();

        //When
        numberList.addItemByIndex(0,42);
        int actual = numberList.size();

        //Then
        assertEquals(1, actual);
    }

    @Test
    public void addItemByIndex() {
        //Given
        MyList<Number> numberList = new MyLinkedList<>();
        numberList.addItem(45);
        numberList.addItem(5);
        numberList.addItem(23);
        numberList.addItem(41);

        //When
        numberList.addItemByIndex(3,42);
        int actual = numberList.size();

        //Then
        assertEquals(5, actual);
    }

    @Test
    public void toStringListIsEmpty() {
        //Given
        MyList<Number> numberList = new MyLinkedList<>();

        //When
        String actual = numberList.toString();

        //Then
        assertEquals("[]", actual);
    }

    @Test
    public void toStringListWithSingleItem() {
        //Given
        MyList<Number> numberList = new MyLinkedList<>();
        numberList.addItem(45);

        //When
        String actual = numberList.toString();
        String expected = "[" + numberList.get(0) + "]";

        //Then
        assertEquals(expected, actual);
    }
    //toStringListWithSeveralItems
    @Test
    public void toStringListWithSeveralItems() {
        //Given
        MyList<Number> numberList = new MyLinkedList<>();
        numberList.addItem(45);
        numberList.addItem(5);
        numberList.addItem(null);

        //When
        String actual = numberList.toString();
        String expected = "[" + numberList.get(0) + ", " + numberList.get(1) + ", " + numberList.get(2) + "]";

        //Then
        assertEquals(expected, actual);
    }
}