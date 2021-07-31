package com.company.map;

import java.util.Set;

public interface MyMap<K, V> {
    V remove(K key);
    V get(K key);
    int size();
    V put(K key, V value);
    V replace(K key, V value);
    boolean containsKey(K key);
    default boolean isEmpty() {
        return size() == 0;
    }
    Set<MyNode<K, V>> getEntries();

    interface MyNode<K, V> {
        K getKey();
        V getValue();
        int getHash();
    }
}
