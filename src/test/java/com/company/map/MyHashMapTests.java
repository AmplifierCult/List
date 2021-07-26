package com.company.map;

public class MyHashMapTests extends BaseMapTests {
    @Override
    protected <K extends Comparable<K>, V> MyMap<K, V> getEmptyMap() { return new MyHashMap<>();
    }
}
