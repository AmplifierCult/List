package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here

        MyArrayList <String> list2 = new MyArrayList<>();
        list2.addItem("one");
        list2.addItem("two");
        list2.addItem("three");
        System.out.println("Size list of ArrayList = " + list2.size());
        System.out.println(list2.get(2));


    }
}
