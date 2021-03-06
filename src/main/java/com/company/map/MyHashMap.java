package com.company.map;

import java.util.HashSet;
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

    /**
     * Метод containsKey() возвращает true если в TreeMap есть указанный ключ key.
     */
    @Override
    public boolean containsKey(K key) {
        validateKeyIsNull(key);
        if (isEmpty()) {
            return false;
        }
        Node bucket = getBucket(key);
        if (bucket == null) {
            return false;
        } else {
            return bucket.containsKeyInBucket(key);
        }
    }

    /**
     * Метод remove() удаляет объект с ключом key. Возвращает предыдущее значение,
     * связанное с указанным ключом.
     * Если такой сопоставленный ключ не отображается, он возвращает null.
     */
    @Override
    public V remove(K key) {
        validateKeyIsNull(key);
        if (isEmpty()) {
            return null;
        }

        if (!containsKey(key)) {
            return null;
        }

        Node bucket = getBucket(key);
        if (bucket == null) {
            return null;
        }
        Node removedElement = bucket.getEqualsNodeByKey(key);

        if (bucket.equals(removedElement) && bucket.nextElement == null) {
            V oldValue = removedElement.value;
            hashTable.set(hash(key), null);
            size--;
            return oldValue;
        }

        Node parent = getParentNode(bucket, removedElement);
        V oldValue = removedElement.value;
        if (removedElement.nextElement == null) {
            parent.nextElement = null;
        } else {
            parent.nextElement = removedElement.nextElement;
        }
        size--;
        return oldValue;
    }

    private Node getParentNode (Node firstNodeInBucket, Node childNode) {
        Node parentNode = firstNodeInBucket;
        Node currentNode = firstNodeInBucket;
        while (currentNode != null) {
            if (currentNode.equals(childNode)) {
                return parentNode;
            }
            parentNode = currentNode;
            currentNode = currentNode.nextElement;
        }
        return parentNode;
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
        if (bucket == null) {
            return null;
        }
        if (bucket.containsKeyInBucket(key)) {
            return bucket.getEqualsNodeByKey(key).value;
        } else {
            return null;
        }
    }

    /**
     * Метод size() возвращает количество элементов TreeMap.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Метод replace() возвращает предыдущее значение, связанное с указанным ключом.
     * Если такой сопоставленный ключ не отображается, он возвращает null.
     */
    @Override
    public V replace(K key, V value) {
        return put(key, value);
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
            hashTable.set(index, bucket);
            size++;
            return null;
        }

        // Если корзина не пуста и содержит такой же ключ как у нового элемента,
        // то перезаписываем старое значение на новое
        if (bucket.containsKeyInBucket(key)) {
            Node equalsNodeKey = bucket.getEqualsNodeByKey(key);
            V oldValue = equalsNodeKey.value;
            equalsNodeKey.value = value;
            equalsNodeKey.hash = equalsNodeKey.hash();
            return oldValue;

        // Если корзина не пуста и не содержит такой же ключ как у нового элемента,
        // добавляем в эту корзину новый элемент
        } else {
            Node lastNodeBucket = bucket.getLastNode();
            lastNodeBucket.nextElement = new Node(key, value);
            size++;
            return null;
        }
    }

    /**
     * Метод getEntries() возвращает множество всех элементов HashMap
     */
    @Override
    public Set<MyNode<K, V>> getEntries() {
        Set<MyNode<K, V>> set = new HashSet<>();
        Node currentElement;
        for (int i = 0; i < CAPACITY; i++) {
            currentElement = hashTable.get(i);
            if (currentElement != null) {
                while (currentElement != null) {
                    set.add(currentElement);
                    currentElement = currentElement.nextElement;
                }
            }
        }
        return set;
    }

    private Node getBucket(K key) {
        return hashTable.get(hash(key));
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

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Map is empty";
        }
        Set<MyNode<K, V>> set = getEntries();
        StringBuilder elementMap = new StringBuilder();
        int index = 1;
        for (MyNode<K, V> node : set) {
            Node castedNode = (Node) node;
            elementMap.append("ElementHashMap ");
            elementMap.append(index);
            elementMap.append(" [");
            elementMap.append("hash = ");
            elementMap.append(castedNode.hash);
            elementMap.append(", key = ");
            elementMap.append(castedNode.key);
            elementMap.append(", value = ");
            elementMap.append(castedNode.value);
            elementMap.append("]\n");
            index++;
        }
        return elementMap.toString();
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

        private boolean containsKeyInBucket(K key) {
            Node currentNode = this;
            while (currentNode != null) {
                if (currentNode.key.equals(key)) {
                    return true;
                }
                currentNode = currentNode.nextElement;
            }
            return false;
        }

        private Node getEqualsNodeByKey(K key) {
            Node currentNode = this;
            while (currentNode != null) {
                if (currentNode.key.equals(key)) {
                    return currentNode;
                }
                currentNode = currentNode.nextElement;
            }
            throw new IllegalStateException();
        }

        private Node getLastNode() {
            Node currentNode = this;
            while (currentNode.nextElement != null) {
                currentNode = currentNode.nextElement;
            }
            return currentNode;
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
