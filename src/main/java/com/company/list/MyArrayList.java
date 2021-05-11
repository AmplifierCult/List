package com.company.list;

public class MyArrayList<E> implements MyList<E> {

    E[] item;

    public MyArrayList() {
        //TODO Сделать отдельный конструктор с заданным размером.
        this.item = (E[]) new Object[0];
    }

    @Override
    public void addItem(E e) {
        E[] temp = item;
        //FIXME Увеличиваешь размер массива при каждом добавлении, требуется проверка на достижение предельного размера
        item = (E[]) new Object[temp.length * 2 + 1];
        System.arraycopy(temp, 0, item, 0, temp.length);
        item[item.length - 1] = e;
    }

    @Override
    public void addItem(int n, E e) {
        //FIXME Перетираешь элемент, а не добавляешь.
        item[n] = e;
    }

    @Override
    public int size() {
        //FIXME Это размер массива, а не количество элементов в списке.
        return item.length;
    }

    @Override
    public void remove(E e) {
        //TODO
    }

    @Override
    public E get(int n) {
        return item [n];
    }

    @Override
    public void removeByIndex (int n) {
        //TODO остается дырка в виде null.
        item[n] = null;
    }
}
