package com.company.map;

import com.company.list.MyList;

public class MyTreeMap<K extends Comparable<K>, V> extends AbstractMap<K, V>{

    private int size;
    private Node root;

    public MyTreeMap() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Метод remove() удаляет объект с ключом key. Возвращает предыдущее значение,
     * связанное с указанным ключом.
     * Если такой сопоставленный ключ не отображается, он возвращает null.
     */
    @Override
    public V remove(K key) {
        validateKeyIsNull(key);
        if (isEmpty() || root.key == null) {
            throw new NullPointerException("Map is empty");
        }

        // если удаляем root
        if (root.key.equals(key)) {
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
        //  Если у root нет дочерних элементов, мы просто удаляем этот узел.
        if (root.leafLeft.key == null && root.leafRight.key == null) {
            root = null;

            // Если у root только один дочерний узел (левый), то мы присваиваем root
            // ссылку на этот дочерний элемент.
        } else if (root.leafLeft.key != null && root.leafRight.key == null) {
            root = root.leafLeft;
            root.parent = null;

            // Если у узла только один дочерний узел (правый), то мы присваиваем root
            // ссылку на этот дочерний элемент.
        } else if (root.leafLeft.key == null) {
            root = root.leafRight;
            root.parent = null;

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
            root = nextElement;
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
        if (replaceableLink.branchName.equals("Right")) {
            replaceableLink.parent.leafRight = replacementLink;
        } else {
            replaceableLink.parent.leafLeft = replacementLink;
        }
        replacementLink.parent = replaceableLink.parent;
    }

    private void swapLinkChild(Node replaceableLink, Node replacementLink) {
        replacementLink.leafLeft = replaceableLink.leafLeft;
        replacementLink.leafLeft.parent = replacementLink;
        replacementLink.leafRight = replaceableLink.leafRight;
        replacementLink.leafRight.parent = replacementLink;
    }

    private void swapAllLink(Node replaceableLink, Node replacementLink) {
        swapLinkParent(replaceableLink, replacementLink);
        swapLinkChild(replaceableLink, replacementLink);
    }

    private Node searchNextElement(Node currentElement) {
        Node nextElement;
        if (currentElement.leafRight.key != null) {
            nextElement = currentElement.leafRight;
            while (nextElement.leafLeft.key != null) {
                nextElement = nextElement.leafLeft;
            }
            return nextElement;
        } else if (currentElement.parent != null) {
            nextElement = currentElement.parent;
            if (currentElement.key == null || nextElement.key == null) {
                throw new NullPointerException();
            }
            while(currentElement.key.compareTo(nextElement.key) > 0) {
                if (nextElement.parent == null) {
                    return nextElement;
                }
                nextElement = nextElement.parent;
                if (nextElement.key == null) {
                    throw new NullPointerException();
                }
            }
            return nextElement;
        } else return currentElement;
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
        return searchPlaceForElement(key).value;
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

    private void initializeLeaf(Node parent) {
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
        } else return put(key, value);
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

    /**
     * Метод containsKey() возвращает true если в TreeMap есть указанный ключ key.
     */
    @Override
    public boolean containsKey(K key) {
        K searchedKey = searchPlaceForElement(key).key;
        if (searchedKey != null) {
            return searchedKey.equals(key);
        } else return false;
    }

    @Override
    public MyList<MyNode<K, V>> getEntries() {
        throw new IllegalStateException();
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
            this.branchName = null;
        }
    }

    // TODO реализовать equals и hashCode

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Map is empty";
        }
        K parentKey;
        Node currentElement = this.minElement();
        StringBuilder elementMap = new StringBuilder();
        for (int i = 0; i != size(); i++) {
            if (isRoot(currentElement)) {
                elementMap.append("RootTreeMap    level ");
                elementMap.append("0");
                elementMap.append(" ");
                parentKey = null;
            } else {
                elementMap.append("ElementTreeMap level ");
                elementMap.append(getLevel(currentElement));
                elementMap.append(" ");
                parentKey = currentElement.parent.key;
            }
            elementMap.append("[key = ");
            elementMap.append(currentElement.key);
            elementMap.append(", value = ");
            elementMap.append(currentElement.value);
            elementMap.append(", branchName = ");
            elementMap.append(currentElement.branchName);
            elementMap.append(", parent.key = ");
            elementMap.append(parentKey);
            elementMap.append(", leafLeft.key = ");
            elementMap.append(currentElement.leafLeft.key);
            elementMap.append(", leafRight.key = ");
            elementMap.append(currentElement.leafRight.key);
            elementMap.append("]\n");
            currentElement = searchNextElement(currentElement);
        }
        return elementMap.toString();
    }

    private Node minElement() {
        if (isEmpty() || root.key == null) {
            throw new NullPointerException();
        }
        Node minElement = root;
        while (minElement.leafLeft.key != null) {
            minElement = minElement.leafLeft;
        }
        return minElement;
    }

    private boolean isRoot(Node currentNode) {
        return  currentNode.equals(root);
    }

    private int getLevel(Node currentElement) {
        int level = 0;
        while(currentElement != root){
            currentElement = currentElement.parent;
            level++;
        }
        return level;
    }
}
