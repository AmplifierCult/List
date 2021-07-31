package com.company.map;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class MyHashMapTests extends BaseMapTests {
    @Override
    protected <K extends Comparable<K>, V> MyMap<K, V> getEmptyMap() { return new MyHashMap<>();
    }

    @Test
    public void deleteWhenSeveralElementsInBucket() {
        //Given
        MyMap<Integer, String> map = getEmptyMap();
        map.put(10, "10");
        map.put(74, "74");
        map.put(138, "138");

        //When
        map.remove(10);

        //Then
        assertEquals(2, map.size());
        assertTrue(map.containsKey(74));
        assertTrue(map.containsKey(138));
    }
}
