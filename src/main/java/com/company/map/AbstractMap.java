package com.company.map;

abstract class AbstractMap<K extends Comparable<K>, V> implements MyMap<K, V> {
    @Override
    public int hashCode() {
        return super.hashCode();
        // TODO получить Set элементов и вызвать hashCode
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (!(obj instanceof MyMap)) {
            return false;
        }
        // TODO получить список вхождений внешнего объекта
        // TODO получить список вхождений текущего объекта
        // TODO сравниваем обе коллекции (без учета порядка следования)

        return super.equals(obj);
    }
}
