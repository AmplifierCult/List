package com.company.list;

import java.util.Iterator;
import java.util.Objects;

public class MyHashMap<K, V> implements MyMap<K, V> {
    /**
     * Количество элементов HashMap.
     */
    private int size;

    /**
    * Массив типа Entry[], который является хранилищем ссылок
    * на списки (цепочки) значений;
    */
    private final Node[] hashTable;


    /**
     * Предельное количество элементов, при достижении которого,
     * размер хэш-таблицы увеличивается вдвое.
     */
    private float threshold;

    /**
     * Коэффициент загрузки. Значение по умолчанию 0.75 является хорошим компромиссом
     * между временем доступа и объемом хранимых данных;
     */
    private final float loadFactor = 0.75f;


    public MyHashMap() {
        hashTable = new Node[16];
        threshold = hashTable.length * 0.75f;
    }

    private int hash(final K key) {
        int hash = 31;
        hash = hash * 17 + key.hashCode();
        return hash % hashTable.length;
    }


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
        size--;
        return false;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        Node node = new Node(key, value);
        int index = node.hash();
        size++;
    }

    @Override
    public void replace(K key, V value) {

    }

    @Override
    public boolean isEmpty(Object o) {
        return false;
    }

    @Override
    public Iterator<V> iterator() {
        return null;
    }

    private class Node<K, V> {
        private final MyList<Node> nodes;
        private int hash;
        private final K key;
        private V value;

        public Node(K key, V value) {
            nodes = new MyLinkedList<>();
            this.key = key;
            this.value = value;
        }

        private MyList<Node> getNodes() {
            return nodes;
        }

        private int hash() {
            return hashCode() % hashTable.length;
        }

        private K getKey() {
            return key;
        }

        private V getValue() {
            return value;
        }

        private void setValue(V value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            hash = 31;
            hash = hash * 17 + key.hashCode();
            return hash;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o instanceof Node) {
                Node node = (Node) o;
                return (Objects.equals(key, node.getKey()) &&
                        Objects.equals(value, node.getValue()) ||
                        Objects.equals(hash, node.hashCode()));
            }
            return false;
        }
    }
}
