package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        MyLinkedList <Object> list = new MyLinkedList<>();
        list.addItem("first");
        list.addItem("second");
        list.addItem(3);
        list.addItem("fourth");
        list.addItem(5);
        list.addItem("sixth");
        list.addItem("seventh");



        System.out.println(list);
        System.out.println("Размер списка " + list.size());

        list.remove(3);

        System.out.println(list);
        System.out.println("Размер списка " + list.size());
    }
}
