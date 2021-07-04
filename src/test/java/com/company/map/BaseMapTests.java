package com.company.map;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public abstract class BaseMapTests {
    protected abstract <K extends Comparable<K>, V> MyMap<K, V> getEmptyMap();

    @Test
    public void put(){
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
    public void remove(){
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
}
