package com.company.list;

public interface MyMap<K, V> extends Iterable<V> {
    boolean keySet();
    boolean value();
    void cloneMap();
    boolean remove();
    Object get(K key);
    int size();
    void put(K key, V value);
    void replace(K key, V value);
    boolean isEmpty(Object o);
}
