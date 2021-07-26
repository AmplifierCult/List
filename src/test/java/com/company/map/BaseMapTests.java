package com.company.map;

import org.junit.Test;

import static org.junit.Assert.*;

public abstract class BaseMapTests {
    protected abstract <K extends Comparable<K>, V> MyMap<K, V> getEmptyMap();

    @Test
    public void putSeveralElements(){
        //Given
        MyMap<Integer, String> map = getEmptyMap();

        //When
        map.put(1, "one");
        map.put(4, "two");

        //Then
        assertEquals("one", map.get(1));
        assertEquals(2, map.size());
    }

    @Test
    public void putAndReplace(){
        //Given
        MyMap<Integer, String> map = getEmptyMap();

        //When
        map.put(1, "one");
        map.put(4, "two");
        map.put(2, "three");
        String actual = map.put(1, "six");

        //Then
        assertEquals("six", map.get(1));
        assertEquals("one", actual);
    }

    @Test
    public void putReturnNullForNewElement(){
        //Given
        MyMap<Integer, String> map = getEmptyMap();

        //When
        map.put(1, "one");
        map.put(4, "two");
        map.put(2, "three");
        String actual = map.put(6, "six");

        //Then
        assertNull(actual);
    }

    @Test
    public void Replace(){
        //Given
        MyMap<Integer, String> map = getEmptyMap();

        //When
        map.put(1, "one");
        map.put(4, "two");
        map.put(2, "three");
        String actual = map.replace(1, "six");

        //Then
        assertEquals("six", map.get(1));
        assertEquals("one", actual);
    }

    @Test
    public void ReplaceReturnNull(){
        //Given
        MyMap<Integer, String> map = getEmptyMap();

        //When
        map.put(1, "one");
        map.put(4, "two");
        map.put(2, "three");
        String actual = map.replace(5, "six");

        //Then
        assertNull(actual);
    }

    @Test
    public void getAnyElement(){
        //Given
        MyMap<Integer, String> map = getEmptyMap();
        map.put(1, "one");
        map.put(4, "two");
        map.put(3, "three");
        map.put(5, "four");
        map.put(10, "five");
        map.put(7, "six");

        //When
        String actualOne = map.get(4);
        String actualTwo = map.get(5);
        String actualThree = map.get(3);
        String actualFour = map.get(10);
        String actualFive = map.get(7);

        //Then
        assertEquals("two", actualOne);
        assertEquals("four", actualTwo);
        assertEquals("three", actualThree);
        assertEquals("five", actualFour);
        assertEquals("six", actualFive);
    }

    @Test
    public void getByRootKey(){
        //Given
        MyMap<Integer, String> map = getEmptyMap();
        map.put(1, "one");
        map.put(4, "two");
        map.put(3, "three");
        map.put(5, "four");
        map.put(10, "five");
        map.put(7, "six");

        //When
        String actual = map.get(1);

        //Then
        assertEquals("one", actual);
    }

    @Test
    public void getByKeyIsNull(){
        //Given
        MyMap<Integer, String> map = getEmptyMap();
        map.put(1, "one");
        map.put(4, "two");
        map.put(3, "three");
        map.put(5, "four");
        map.put(10, "five");
        map.put(7, "six");

        //Then
        assertThrows(IllegalArgumentException.class, ()-> map.get(null));
    }

    @Test
    public void getByKeyThatDoesNotExist(){
        //Given
        MyMap<Integer, String> map = getEmptyMap();
        map.put(1, "one");
        map.put(4, "two");
        map.put(3, "three");
        map.put(5, "four");
        map.put(10, "five");
        map.put(7, "six");

        //When
        String actual = map.get(11);

        //Then
        assertNull(actual);
    }

    @Test
    public void removeRoot(){
        //Given
        MyMap<Integer, String> map = getEmptyMap();
        map.put(1, "one");
        map.put(4, "two");
        map.put(3, "three");
        map.put(5, "four");
        map.put(10, "five");
        map.put(7, "six");

        //When
        map.remove(1);

        //Then
        assertEquals(5, map.size());
        assertFalse(map.containsKey(1));
    }

    @Test
    public void removeElementWithoutChild(){
        //Given
        MyMap<Integer, String> map = getEmptyMap();
        map.put(1, "one");
        map.put(4, "two");
        map.put(3, "three");
        map.put(5, "four");
        map.put(10, "five");
        map.put(7, "six");

        //When
        map.remove(3);

        //Then
        assertEquals(5, map.size());
        assertFalse(map.containsKey(3));
    }

    @Test
    public void removeElementWithRightChild(){
        //Given
        MyMap<Integer, String> map = getEmptyMap();
        map.put(1, "one");
        map.put(4, "two");
        map.put(3, "three");
        map.put(5, "four");
        map.put(10, "five");
        map.put(7, "six");

        //When
        map.remove(5);

        //Then
        assertEquals(5, map.size());
        assertFalse(map.containsKey(5));
    }

    @Test
    public void removeElementWithLeftChild(){
        //Given
        MyMap<Integer, String> map = getEmptyMap();
        map.put(1, "one");
        map.put(4, "two");
        map.put(3, "three");
        map.put(5, "four");
        map.put(10, "five");
        map.put(7, "six");

        //When
        map.remove(10);

        //Then
        assertEquals(5, map.size());
        assertFalse(map.containsKey(10));
    }

    @Test
    public void removeElementWithTwoChild(){
        //Given
        MyMap<Integer, String> map = getEmptyMap();
        map.put(1, "one");
        map.put(4, "two");
        map.put(3, "three");
        map.put(5, "four");
        map.put(10, "five");
        map.put(7, "six");

        //When
        map.remove(4);

        //Then
        assertEquals(5, map.size());
        assertFalse(map.containsKey(4));
    }

    @Test
    public void removeElementReturnValue(){
        //Given
        MyMap<Integer, String> map = getEmptyMap();
        map.put(1, "one");
        map.put(4, "two");
        map.put(3, "three");
        map.put(5, "four");
        map.put(10, "five");
        map.put(7, "six");

        //When
        String actual = map.remove(4);

        //Then
        assertEquals("two", actual);
    }

    @Test
    public void removeElementReturnNull(){
        //Given
        MyMap<Integer, String> map = getEmptyMap();
        map.put(1, "one");
        map.put(4, "two");
        map.put(3, "three");
        map.put(5, "four");
        map.put(10, "five");
        map.put(7, "six");

        //When
        String actual = map.remove(11);

        //Then
        assertNull(actual);
    }

    @Test
    public void heapObjectsKeys() {
        //Given
        MyMap<Long, String> map = getEmptyMap();
        map.put(1L, "one");
        map.put(4L, "two");
        map.put(3L, "three");
        map.put(5L, "four");
        map.put(10L, "five");
        map.put(7L, "six");
        map.put(8L, "eight");

        //When
        assertEquals(7, map.size());

        map.remove(5L);
        assertEquals(6, map.size());
        assertFalse(map.containsKey(5L));

        map.remove(1L);
        assertEquals(5, map.size());
        assertFalse(map.containsKey(1L));

        map.put(6L, "six");
        assertEquals(6, map.size());
        assertTrue(map.containsKey(6L));

        map.put(1L, "one");
        assertEquals(7, map.size());
        assertTrue(map.containsKey(1L));

        map.remove(10L);
        assertEquals(6, map.size());
        assertFalse(map.containsKey(10L));

        //Then
        Long[] containedKeys = new Long[] {1L, 3L, 4L, 6L, 7L, 8L};
        for (Long containedKey : containedKeys) {
            assertTrue(map.containsKey(containedKey));
        }
    }

    @Test
    public void equals() {
        //Given
        MyMap<Long, String> map1 = getEmptyMap();
        map1.put(1L, "one");
        map1.put(4L, "two");
        map1.put(3L, "three");

        MyMap<Long, String> map2 = getEmptyMap();
        map2.put(1L, "one");
        map2.put(4L, "two");
        map2.put(3L, "three");

        MyMap<Long, String> map3 = getEmptyMap();
        map3.put(1L, "one");
        map3.put(4L, "two");
        map3.put(5L, "three");

        //When
        boolean actual_1 = map1.equals(map2);
        boolean actual_2 = map1.equals(map3);

        //Then
        assertTrue(actual_1);
        assertFalse(actual_2);
    }
}
