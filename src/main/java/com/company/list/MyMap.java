package com.company.list;

public interface MyMap<K, V> {
    V remove(K key);
    V get(K key);
    int size();
    V put(K key, V value);
    V replace(K key, V value);
    default boolean isEmpty() {
        return size() == 0;
    }
}
