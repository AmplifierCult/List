package com.company.list;

public class MyTreeMap<K extends Comparable<K>, V> implements MyMap<K, V>{

    private int size;
    private Node elementMap;
    private Node root;

    public MyTreeMap() {
        this.size = 0;
    }

    private void validateKeyIsNull(K key) {
        if (key == null) {
            throw new IllegalArgumentException("The key must not be null.");
        }
    }

    private Node searchPlaceForElement(K key) {
        Node currentNode = root;
        while (currentNode.key != null) {
            if (currentNode.key.compareTo(key) < 0) {
                currentNode = currentNode.leafLeft;
            } else if (currentNode.key.compareTo(key) > 0) {
                currentNode = currentNode.leafRight;
            } else if (currentNode.key.compareTo(key) == 0) {
                return currentNode;
            }
        }
        return currentNode;
    }

    private Node searchNextElement(Node currentElement) {
        Node nextElement = currentElement.leafRight;
        while (nextElement.leafLeft != null) {
            nextElement = nextElement.leafLeft;
        }
        return nextElement;
    }

    private boolean containsKey(K key) {
        return searchPlaceForElement(key) != null;
    }

    /**
     * Метод remove() удаляет объект с ключом k. Возвращает предыдущее значение,
     * связанное с указанным ключом.
     * Если такой сопоставленный ключ не отображается, он возвращает null.
     */
    @Override
    public V remove(K key) {
        validateKeyIsNull(key);
        if (isEmpty()) {
            return null;
        }
        // TODO не будет работать если удалять root
        if (containsKey(key)) {
            Node removedElement = searchPlaceForElement(key);

            //  Если у узла нет дочерних элементов, мы просто удаляем этот узел.
            if (removedElement.leafLeft.key == null && removedElement.leafRight.key == null) {
                if (removedElement.parent.leafRight == removedElement) {
                    removedElement.parent.leafRight = new Node();
                } else removedElement.parent.leafLeft = new Node();
            }

            // Если у узла только один дочерний узел (левый), то мы удаляем узел, и создаем связь
            // между его дочерним и родительским элементами.
            if (removedElement.leafLeft.key != null && removedElement.leafRight.key == null) {
                removedElement.leafLeft.colorNode = removedElement.colorNode;
                if (removedElement.parent.leafRight == removedElement) {
                    removedElement.parent.leafRight = removedElement.leafLeft;
                } else removedElement.parent.leafLeft = removedElement.leafLeft;
            }

            // Если у узла только один дочерний узел (правый), то мы удаляем узел, и создаем связь
            // между его дочерним и родительским элементами.
            if (removedElement.leafLeft.key == null && removedElement.leafRight.key != null) {
                removedElement.leafRight.colorNode = removedElement.colorNode;
                if (removedElement.parent.leafRight == removedElement) {
                    removedElement.parent.leafRight = removedElement.leafRight;
                } else removedElement.parent.leafLeft = removedElement.leafRight;
            }

            // Если у узла два дочерних элемента. Мы находим следующий за ним по возрастанию узел,
            // у которого нет левого дочернего узла.
            if (removedElement.leafLeft.key != null && removedElement.leafRight.key != null) {

                //находим следующий за ним по возрастанию узел
                Node nextElement = searchNextElement(removedElement);

                // если у следующего узла есть правый потомок
                if (nextElement.leafRight.key != null) {

                    // перепривязываем потомка к родителю nextElement
                    nextElement.parent.leafLeft = nextElement.leafRight;
                    nextElement.parent.leafLeft.colorNode = nextElement.colorNode;

                    // ставим nextElement на место удаляемого элемента
                    nextElement.colorNode = removedElement.colorNode;
                    nextElement.leafLeft = removedElement.leafLeft;
                    nextElement.leafRight = removedElement.leafRight;
                    if (removedElement.parent.leafRight == removedElement) {
                        removedElement.parent.leafRight = nextElement;
                    } else removedElement.parent.leafLeft = nextElement;

                // если у следущего узла нет потомков
                } else {
                    // ставим nextElement на место удаляемого элемента
                    nextElement.colorNode = removedElement.colorNode;
                    nextElement.leafLeft = removedElement.leafLeft;
                    nextElement.leafRight = removedElement.leafRight;
                    if (removedElement.parent.leafRight == removedElement) {
                        removedElement.parent.leafRight = nextElement;
                    } else removedElement.parent.leafLeft = nextElement;
                }
            }
            size--;
            return removedElement.value;
        } else return null;
    }

    /**
     * Метод get() возвращает значение объекта, ключ которого равен k.
     * Если такого элемента не окажется, то возвращается значение null
     */
    @Override
    public V get(K key) {
        validateKeyIsNull(key);
        if (isEmpty()) {
            return null;
        }
        return searchPlaceForElement(key).value;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Метод put() помещает в коллекцию новый объект с ключом k и значением v.
     * Если в коллекции уже есть объект с подобным ключом, то он перезаписывается.
     * После добавления возвращает предыдущее значение для ключа k, если он уже был в коллекции.
     * Если же ключа еще не было в коллекции, то возвращается значение null.
     */
    @Override
    public V put(K key, V value) {
        validateKeyIsNull(key);
        if (isEmpty()) {
            root = new Node(key, value);
        } else if (containsKey(key)) {
            return replace(key, value);
        } else new Node(searchPlaceForElement(key).parent, key, value);
        size++;
        return null;
    }

    /**
     * Метод replace() возвращает предыдущее значение, связанное с указанным ключом.
     * Если такой сопоставленный ключ не отображается, он возвращает null.
     */
    @Override
    public V replace(K key, V value) {
        validateKeyIsNull(key);
        if (containsKey(key)) {
            Node searchedElement = searchPlaceForElement(key);
            V replacedValue = searchedElement.value;
            searchedElement.value = value;
            return replacedValue;
        } else return null;
    }

    private class Node {
        private String colorNode;
        private K key;
        private V value;
        private Node leafLeft;
        private Node leafRight;
        private Node parent;

        public Node(Node parent, K key, V value) {
            this.colorNode = "Red";
            this.key = key;
            this.value = value;
            this.leafLeft = new Node();
            this.leafRight = new Node();
            this.parent = parent;
        }

        public Node(K key, V value) {
            this.colorNode = "Black";
            this.key = key;
            this.value = value;
            this.leafLeft = new Node();
            this.leafRight = new Node();
        }

        public Node() {
            this.colorNode = "Black";
        }
    }

    @Override
    // TODO реализовать toString.
    public String toString() {
        if (size() == 1) {
            elementMap = root;
        }

        return "MyTreeMap{" +
                "elementMap " + 1 +
                ", colorNode=" + elementMap.colorNode +
                ", key=" + elementMap.key +
                ", value=" + elementMap.value +
                ", parent=" + elementMap.parent +
                '}';
    }
}
