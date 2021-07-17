package com.company.map;

import java.util.Set;

abstract class AbstractMyMap<K extends Comparable<K>, V> implements MyMap<K, V> {

    @Override
    public int hashCode() {
        return this.getEntries().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (!(obj instanceof MyMap)) {
            return false;
        } else if (obj == this) {
            return true;
        } else {
            Set<? extends MyNode<?, ?>> set1 = ((MyMap<?, ?>) obj).getEntries();
            Set<MyNode<K, V>> set2 = this.getEntries();
            return set1.equals(set2);
        }
    }
}