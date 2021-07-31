package com.company.list;

import com.company.map.MyHashMap;
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

        MyMap<Long, String> map1 = new MyTreeMap<>();
        map1.put(1L, "one");
        map1.put(4L, "two");
        map1.put(3L, "three");

        MyMap<Long, String> map2 = new MyTreeMap<>();
        map2.put(1L, "one");
        map2.put(4L, "two");
        map2.put(3L, "three");

        MyMap<Long, String> map3 = new MyTreeMap<>();
        map3.put(1L, "one");
        map3.put(4L, "two");
        map3.put(5L, "three");

        //When
        boolean actual_1 = map1.equals(map2);
        boolean actual_2 = map1.equals(map3);

        System.out.println(actual_1);
        System.out.println(actual_2);

        System.out.println(map1.hashCode());
        System.out.println(map2.hashCode());
        System.out.println(map3.hashCode());

        MyMap<Integer, String> map5 = new MyHashMap<>();
        map5.put(1, "one");
        map5.put(4, "two");
        map5.put(3, "three");
        map5.put(5, "four");
        map5.put(10, "five");
        map5.put(7, "six");
        System.out.println(map5);

    }
}
