package com.company.map;

import java.util.Objects;
import java.util.Set;
import java.util.ArrayList;

public class MyHashMap<K extends Comparable<K>, V> extends AbstractMyMap<K, V> {
    /**
     * Количество элементов HashMap.
     */
    private int size;

    /**
     * Ёмкость массива элементов HashMap.
     */
    private final int CAPACITY = 64;

    /**
    * Список типа ArrayList, который является хранилищем ссылок
    * на списки (цепочки) значений;
    */
    private final ArrayList<Node> hashTable;

    public MyHashMap() {
        this.hashTable = arrayCreator();
    }

    private ArrayList<Node> arrayCreator() {
        ArrayList<Node> arr = new ArrayList<>();
        for (int i = 0; i < CAPACITY; i++) {
            arr.add(null);
        }
        return arr;
    }

    public int hash(final K key) {
        validateKeyIsNull(key);
        int hash = 31;
        hash = hash * 17 + key.hashCode();
        return hash % CAPACITY;
    }

    private void validateKeyIsNull(K key) {
        if (key == null) {
            throw new IllegalArgumentException("The key must not be null.");
        }
    }

    /**
     * Метод containsKey() возвращает true если в TreeMap есть указанный ключ key.
     */
    @Override
    public boolean containsKey(K key) {
        validateKeyIsNull(key);
        isEmpty();
        Node bucket = getBucket(key);
        return containsKeyInBucket(bucket, key);
    }

    /**
     * Метод remove() удаляет объект с ключом key. Возвращает предыдущее значение,
     * связанное с указанным ключом.
     * Если такой сопоставленный ключ не отображается, он возвращает null.
     */
    // TODO to realize remove
    @Override
    public V remove(K key) {
        if (containsKey(key)) {
            Node removedElement = getEqualsForKeyNodeBucket(getBucket(key), key);

            size--;
            return removedElement.value;

        } else return null;
    }

    /**
     * Метод get() возвращает значение объекта, ключ которого равен key.
     * Если такого элемента не окажется, то возвращается значение null.
     */
    @Override
    public V get(K key) {
        validateKeyIsNull(key);
        if (isEmpty()) {
            return null;
        }
        Node bucket = getBucket(key);
        if (containsKeyInBucket(bucket, key)) {
            return getEqualsForKeyNodeBucket(bucket, key).value;
        } else return null;
    }

    /**
     * Метод size() возвращает количество элементов TreeMap.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Метод put() помещает в коллекцию новый объект с ключом key и значением value.
     * Если в коллекции уже есть объект с подобным ключом, то он перезаписывается.
     * После добавления возвращает предыдущее значение для ключа key, если он уже был в коллекции.
     * Если же ключа еще не было в коллекции, то возвращается значение null.
     */
    @Override
    public V put(K key, V value) {
        validateKeyIsNull(key);
        int index = hash(key);
        Node bucket = getBucket(key);

        // Если искомая корзина пуста, создаем новый узел
        if (bucket == null) {
            bucket = new Node(key, value);
            hashTable.add(index, bucket);
            size++;
            return null;
        }

        // Если корзина не пуста и содержит такой же ключ как у нового элемента,
        // то перезаписываем старое значение на новое
        if (containsKeyInBucket(bucket, key)) {
            Node equalsNodeKey = getEqualsForKeyNodeBucket(bucket, key);
            V oldValue = equalsNodeKey.value;
            equalsNodeKey.value = value;
            equalsNodeKey.hash = equalsNodeKey.hash();
            return oldValue;

        // Если корзина не пуста и не содержит такой же ключ как у нового элемента,
        // добавляем в эту корзину новый элемент
        } else {
            Node lastNodeBucket = getLastNodeBucket(bucket);
            lastNodeBucket.nextElement = new Node(key, value);
            size++;
            return null;
        }
    }

    private boolean containsKeyInBucket(Node firstNodeInBucket, K key) {
        Node currentNode = firstNodeInBucket;
        do {
            if (currentNode.key.equals(key)) {
                return true;
            }
            currentNode = currentNode.nextElement;
        } while (currentNode.nextElement != null);
        return false;
    }

    private Node getEqualsForKeyNodeBucket(Node firstNodeInBucket, K key) {
        Node currentNode = firstNodeInBucket;
        do {
            if (currentNode.key.equals(key)) {
                return currentNode;
            }
            currentNode = currentNode.nextElement;
        } while (currentNode.nextElement != null);
        throw new IllegalStateException();
    }

    private Node getLastNodeBucket(Node firstNodeInBucket) {
        Node currentNode = firstNodeInBucket;
        while (currentNode.nextElement != null) {
            currentNode = currentNode.nextElement;
        }
        return currentNode;
    }

    private Node getBucket(K key) {
        return hashTable.get(hash(key));
    }

    /**
     * Метод replace() возвращает предыдущее значение, связанное с указанным ключом.
     * Если такой сопоставленный ключ не отображается, он возвращает null.
     */
    // TODO to realize replace
    @Override
    public V replace(K key, V value) {
        validateKeyIsNull(key);
        throw new IllegalStateException("Not implemented yet.");
    }

    /**
     * Метод getEntries() возвращает множество всех элементов HashMap
     */
    // TODO to realize getEntries
    @Override
    public Set<MyNode<K, V>> getEntries() {
        throw new IllegalStateException();
    }


    private class Node implements MyNode<K, V> {
        private int hash;
        private final K key;
        private V value;
        private Node nextElement;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.hash = hash();
            this.nextElement = null;
        }

        private int hash() {
            return hashCode() % CAPACITY;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o instanceof MyHashMap.MyNode) {
                MyMap.MyNode<?,?> e = (MyMap.MyNode<?,?>)o;
                return (Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue()) ||
                        Objects.equals(hash, e.hashCode()));
            }
            return false;
        }
    }
}
