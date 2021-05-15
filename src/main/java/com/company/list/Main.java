package com.company.list;

public class Main {

    public static void main(String[] args) {
	// write your code here

        /*MyArrayList <String> list2 = new MyArrayList<>();
        list2.addItem("one");
        list2.addItem("two");
        list2.addItem("three");
        System.out.println("Size list of ArrayList = " + list2.size());
        System.out.println(list2.get(2));*/

        MyLinkedList <Object> list = new MyLinkedList<>();
        list.addItem("first");
        list.addItem("second");
        list.addItem(3);
        list.addItem("fourth");
        list.addItem(5);
        list.addItem("sixth");
        list.addItem("seventh");
        System.out.println(list);

    }
}
