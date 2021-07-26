package com.company.map;

public class MyTreeMapTests extends BaseMapTests {
    @Override
    protected <K extends Comparable<K>, V> MyMap<K, V> getEmptyMap() { return new MyTreeMap<>();
    }
}
