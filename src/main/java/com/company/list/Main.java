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
        System.out.println(list.hashCode());


        MyLinkedList <Object> list2 = new MyLinkedList<>();
        list2.addItem("first");
        list2.addItem("second");
        list2.addItem(3);
        list2.addItem("fourth");
        list2.addItem(5);
        list2.addItem("sixth");
        list2.addItem("seventh");
        System.out.println(list2);
        System.out.println(list2.hashCode());
        System.out.println(list2.equals(list));
    }
}
