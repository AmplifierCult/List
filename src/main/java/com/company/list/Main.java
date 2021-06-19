package com.company.list;

public class Main {

    public static void main(String[] args) {
	// write your code here

        MyArrayList <String> list2 = new MyArrayList<>(3);
        System.out.println("Size list of ArrayList = " + list2.size());
        MyArrayList <String> list1 = new MyArrayList<>();
        System.out.println("Size list of ArrayList = " + list1.size());
        list1.addItem("qwerty");
        list1.addItem("qwe");
        list1.addItem("qty");
        list1.addItem("qwy");
        System.out.println("Size list of ArrayList = " + list1.size());
        System.out.println("Size item of ArrayList = " + list1.item.length);

    }
}
