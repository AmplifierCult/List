package com.company.list;

import com.company.map.MyMap;
import com.company.map.MyTreeMap;

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

        MyMap<Float, String> map = new MyTreeMap<>();
        map.put(5.0f, "one");
        map.put(4.13f, "two");
        map.put(6.05f, "three");
        map.put(7.0f, "four");
        map.put(2.53f, "five");
        map.put(1.75f, "six");
        System.out.println(map);

        map.remove(5.0f);
        System.out.println(map);
    }
}
