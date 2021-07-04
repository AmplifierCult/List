package com.company.map;

public class MyTreeMap<K extends Comparable<K>, V> implements MyMap<K, V>{

    private int size;
    private Node root;

    public MyTreeMap() {
        this.root = null;
        this.size = 0;
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
            throw new NullPointerException("Map is empty");
        }

        // если удаляем root
        if (root.key == key) {
            V removedValue = root.value;
            removeRoot();
            size--;
            return removedValue;
        }
        if (containsKey(key)) {
            Node removedElement = searchPlaceForElement(key);

            //  Если у узла нет дочерних элементов, мы просто удаляем этот узел.
            if (removedElement.leafLeft.key == null && removedElement.leafRight.key == null) {
                removeElementWithoutChild(removedElement);
            }

            // Если у узла только один дочерний узел (левый), то мы удаляем узел, и создаем связь
            // между его дочерним и родительским элементами.
            if (removedElement.leafLeft.key != null && removedElement.leafRight.key == null) {
                removeElementWithLeftChild(removedElement);
            }

            // Если у узла только один дочерний узел (правый), то мы удаляем узел, и создаем связь
            // между его дочерним и родительским элементами.
            if (removedElement.leafLeft.key == null && removedElement.leafRight.key != null) {
                removeElementWithRightChild(removedElement);
            }

            // Если у узла два дочерних элемента. Мы находим следующий за ним по возрастанию узел,
            // у которого нет левого дочернего узла.
            if (removedElement.leafLeft.key != null && removedElement.leafRight.key != null) {
                removeElementWithTwoChild(removedElement);
            }
            size--;
            return removedElement.value;
        } else return null;
    }

    private void removeRoot() {
        //  Если у узла нет дочерних элементов, мы просто удаляем этот узел.
        if (root.leafLeft.key == null && root.leafRight.key == null) {
            root = null;

            // Если у узла только один дочерний узел (левый), то мы удаляем узел, и создаем связь
            // между его дочерним и родительским элементами.
        } else if (root.leafLeft.key != null && root.leafRight.key == null) {
            root = root.leafLeft;

            // Если у узла только один дочерний узел (правый), то мы удаляем узел, и создаем связь
            // между его дочерним и родительским элементами.
        } else if (root.leafLeft.key == null) {
            root = root.leafRight;

            // Если у узла два дочерних элемента. Мы находим следующий за ним по возрастанию узел,
            // у которого нет левого дочернего узла.
        } else {
            Node nextElement = searchNextElement(root);

            // если у nextElement есть правый потомок
            if (nextElement.leafRight.key != null) {

                // перепривязываем потомка nextElement к его родителю
                swapLinkParent(nextElement, nextElement.leafRight);
            }

            // ставим nextElement на место удаляемого элемента
            swapLinkChild(root, nextElement);
        }
    }

    private void removeElementWithoutChild(Node removedElement) {
        swapLinkParent(removedElement, new Node());
    }

    private void removeElementWithRightChild(Node removedElement) {
        swapLinkParent(removedElement, removedElement.leafRight);
    }

    private void removeElementWithLeftChild(Node removedElement) {
        swapLinkParent(removedElement, removedElement.leafLeft);
    }

    private void removeElementWithTwoChild(Node removedElement) {
        //находим следующий за удаляемым по возрастанию узел
        Node nextElement = searchNextElement(removedElement);

        // если у nextElement есть правый потомок
        if (nextElement.leafRight.key != null) {

            // перепривязываем потомка nextElement к его родителю
            swapLinkParent(nextElement, nextElement.leafRight);
        }
        
        // ставим nextElement на место удаляемого элемента
        swapAllLink(removedElement, nextElement);
    }

    private void swapLinkParent(Node replaceableLink, Node replacementLink) {
        if (replaceableLink.parent.leafRight == replaceableLink) {
            replaceableLink.parent.leafRight = replacementLink;
        } else replaceableLink.parent.leafLeft = replacementLink;
    }

    private void swapLinkChild(Node replaceableLink, Node replacementLink) {
        replacementLink.leafLeft = replaceableLink.leafLeft;
        replacementLink.leafRight = replaceableLink.leafRight;
    }

    private void swapAllLink(Node replaceableLink, Node replacementLink) {
        swapLinkParent(replaceableLink, replacementLink);
        swapLinkChild(replaceableLink, replacementLink);
    }

    private Node searchNextElement(Node currentElement) {
        Node nextElement = currentElement.leafRight;
        while (nextElement.leafLeft != null) {
            nextElement = nextElement.leafLeft;
        }
        return nextElement;
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
        Node searchedElement;
        Node newElement;
        if (isEmpty()) {
            root = new Node(key, value);
            initializeLeaf(root);
        } else if (containsKey(key)) {
            return replace(key, value);
        } else {
            searchedElement = searchPlaceForElement(key);
            newElement = new Node(searchedElement.parent,searchedElement.branchName, key, value);
            if (newElement.branchName.equals("Right")) {
                newElement.parent.leafRight = newElement;
            } else newElement.parent.leafLeft = newElement;
            initializeLeaf(newElement);
        }
        size++;
        return null;
    }

    public void initializeLeaf(Node parent) {
        parent.leafLeft.parent = parent;
        parent.leafLeft.branchName = "Left";
        parent.leafRight.parent = parent;
        parent.leafRight.branchName = "Right";
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

    private void validateKeyIsNull(K key) {
        if (key == null) {
            throw new IllegalArgumentException("The key must not be null.");
        }
    }

    private Node searchPlaceForElement(K key) {
        Node currentNode = root;
        while (currentNode.key != null) {
            if (currentNode.key.compareTo(key) > 0) {
                currentNode = currentNode.leafLeft;
            } else if (currentNode.key.compareTo(key) < 0) {
                currentNode = currentNode.leafRight;
            } else if (currentNode.key.compareTo(key) == 0) {
                return currentNode;
            }
        }
        return currentNode;
    }

    @Override
    public boolean containsKey(K key) {
        return searchPlaceForElement(key).key == key;
    }

    private class Node {
        private final K key;
        private V value;
        private String branchName;
        private Node leafLeft;
        private Node leafRight;
        private Node parent;

        public Node(Node parent, String branchName, K key, V value) {
            this.key = key;
            this.value = value;
            this.leafLeft = new Node();
            this.leafRight = new Node();
            this.parent = parent;
            this.branchName = branchName;
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.leafLeft = new Node();
            this.leafRight = new Node();
        }

        public Node() {
            this.key = null;
            this.value = null;
            this.parent = null;
        }
    }

    @Override
    // TODO реализовать toString.
    public String toString() {


        return null;
    }
}
