package com.company.map;

import com.company.list.MyList;

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
    // TODO заменить MyList на Set
    MyList<MyNode<K, V>> getEntries();

    public interface MyNode<K, V> {
        K getKey();
        V getValue();
    }
}
