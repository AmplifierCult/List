package com.company.list;

import java.util.Iterator;

public class MyHashMap<E> implements MyMap {
    @Override
    public boolean keySet() {
        return false;
    }

    @Override
    public boolean value() {
        return false;
    }

    @Override
    public void cloneMap() {

    }

    @Override
    public boolean remove() {
        return false;
    }

    @Override
    public Object get(Object key) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void put(Object key, Object value) {

    }

    @Override
    public void replace(Object key, Object value) {

    }

    @Override
    public boolean isEmpty(Object o) {
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }
}
