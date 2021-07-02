package com.company.list;

import org.junit.Test;
import static org.junit.Assert.*;

public abstract class BaseMapTests {
    protected abstract <K extends Comparable<K>, V> MyMap<K, V> getEmptyMap();

    @Test
    public void remove(){
        //Given
        MyMap<Integer, String> map = getEmptyMap();
        map.put(1, "asd");
        map.put(4, "asdf");
        map.put(3, "ayyyf");
        map.put(5, "afff");
        map.put(10, "ajjjjd");
        map.put(7, "abbb");

        //When
        map.remove(5);
        int actual = map.size();

        //Then
        assertEquals(5, actual);
    }
}
