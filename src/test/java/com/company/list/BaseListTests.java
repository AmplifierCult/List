package com.company.list;

import org.junit.Test;

import static org.junit.Assert.*;

public abstract class BaseListTests {

    protected abstract <E> MyList<E> getEmptyList();

    @Test
    public void addItem() {
        //Given
        MyList<Object> list = getEmptyList();

        //When
        list.addItem("first");
        list.addItem("second");
        list.addItem(3);
        list.addItem("fourth");
        list.addItem(5);
        list.addItem("sixth");
        list.addItem("seventh");

        //Then
        assertEquals(7, list.size());
        assertEquals("seventh", list.get(6));
    }

    @Test
    public void size() {
        //Given
        MyList<Object> list = getEmptyList();

        //When
        list.addItem("first");
        list.addItem("second");
        list.addItem(3);

        //Then
        assertEquals(3, list.size());
    }

    @Test
    public void sizeOfEmptyList() {
        //Given
        MyList<Object> list = getEmptyList();

        //When
        int actual = list.size();

        //Then
        assertEquals(0, actual);
    }

    @Test
    public void sizeOfListWhenAllElementsIsNull() {
        //Given
        MyList<Object> list = getEmptyList();
        list.addItem(null);
        list.addItem(null);
        list.addItem(null);

        //When
        int actual = list.size();

        //Then
        assertEquals(3, actual);
    }

    @Test
    public void sizeOfListWhenSeveralElementsIsNull() {
        //Given
        MyList<Object> list = getEmptyList();
        list.addItem(null);
        list.addItem(2);
        list.addItem(null);

        //When
        int actual = list.size();

        //Then
        assertEquals(3, actual);
    }

    @Test
    public void remove() {
        //Given
        MyList<Object> list = getEmptyList();
        list.addItem("one");
        list.addItem(2);
        list.addItem(3);

        //When
        list.remove(2);

        //Then
        assertEquals(2, list.size());
        assertEquals(3, list.get(1));
    }

    @Test
    public void removeWhenListHaveEqualsElements() {
        //Given
        MyList<Object> list = getEmptyList();
        list.addItem("one");
        list.addItem("one");
        list.addItem(3);
        list.addItem(2);
        list.addItem(2);

        //When
        list.remove(2);

        //Then
        assertEquals(4, list.size());
        assertEquals(3, list.get(2));
        assertEquals(2, list.get(3));
    }

    @Test
    public void get() {
        //Given
        MyList<Object> list = getEmptyList();
        list.addItem("one");
        list.addItem("two");
        list.addItem(3);

        //When
        Object actual = list.get(1);

        //Then
        assertEquals("two", actual);
    }

    @Test
    public void removeByIndex() {
        //Given
        MyList<Object> list = getEmptyList();
        list.addItem("one");
        list.addItem("two");
        list.addItem(3);
        list.addItem(4);
        list.addItem(5);

        //When
        list.removeByIndex(1);

        //Then
        assertEquals(4, list.size());
        assertEquals(3, list.get(1));
    }

    @Test
    public void deleteItemFromListSizeOne() {
        //Given
        MyList<Number> numberList = getEmptyList();
        numberList.addItem(1);

        //When
        numberList.remove(1);

        //Then
        assertEquals(0, numberList.size());
    }

    @Test
    public void deleteLastItemFromListSizeTwo() {
        //Given
        MyList<Number> numberList = getEmptyList();
        numberList.addItem(1);
        numberList.addItem(2);

        //When
        numberList.remove(2);

        //Then
        assertEquals(1, numberList.size());
        assertEquals(1, numberList.get(0));
    }

    @Test
    public void deleteFirstItemFromListSizeTwo() {
        //Given
        MyList<Number> numberList = getEmptyList();
        numberList.addItem(1);
        numberList.addItem(2);

        //When
        numberList.remove(1);

        //Then
        assertEquals(1, numberList.size());
        assertEquals(2, numberList.get(0));
    }

    @Test
    public void indexOfWhenListIsEmpty() {
        //Given
        MyList<Number> numberList = getEmptyList();

        //When
        int actual = numberList.indexOf(0);

        //Then
        assertEquals(-1, actual);
    }

    @Test
    public void indexOfWhenFirstElement() {
        //Given
        MyList<Number> numberList = getEmptyList();
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
        MyList<Number> numberList = getEmptyList();
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
        MyList<Number> numberList = getEmptyList();
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
        MyList<Number> numberList = getEmptyList();
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
        MyList<Number> numberList = getEmptyList();
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
        MyList<Number> numberList = getEmptyList();
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
        MyList<Number> numberList = getEmptyList();
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
        MyList<Number> numberList = getEmptyList();
        numberList.addItem(45);

        //When
        numberList.addItemByIndex(0,42);
        int actual = numberList.size();

        //Then
        assertEquals(2, actual);
        assertEquals(42, numberList.get(0));
    }

    @Test
    public void addItemByIndexWhenListEmpty() {
        //Given
        MyList<Number> numberList = getEmptyList();

        //When
        numberList.addItemByIndex(0,42);
        int actual = numberList.size();

        //Then
        assertEquals(1, actual);
        assertEquals(42, numberList.get(0));
    }

    @Test
    public void addItemByIndex() {
        //Given
        MyList<Number> numberList = getEmptyList();
        numberList.addItem(45);
        numberList.addItem(5);
        numberList.addItem(23);
        numberList.addItem(41);

        //When
        numberList.addItemByIndex(3,42);
        int actual = numberList.size();

        //Then
        assertEquals(5, actual);
        assertEquals(42, numberList.get(3));
    }

    @Test
    public void toStringListIsEmpty() {
        //Given
        MyList<Number> numberList = getEmptyList();

        //When
        String actual = numberList.toString();

        //Then
        assertEquals("[]", actual);
    }

    @Test
    public void toStringListWithSingleItem() {
        //Given
        MyList<Number> numberList = getEmptyList();
        numberList.addItem(45);

        //When
        String actual = numberList.toString();
        String expected = "[" + numberList.get(0) + "]";

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void toStringListWithSeveralItems() {
        //Given
        MyList<Number> numberList = getEmptyList();
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