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
        System.out.println(list1);
        list1.removeByIndex(1);
        list1.removeByIndex(1);
        list1.removeByIndex(0);
        list1.removeByIndex(0);
        System.out.println(list1.getClass());
        Integer one = 1;
        Integer four = 4;
        System.out.println(four.compareTo(one));
    }
}
