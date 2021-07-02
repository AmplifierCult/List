package com.company.list;

import java.util.HashMap;
import java.util.Map;

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
        HashMap <String, String> map = new HashMap<>();
        /*map.size();
        map.get();
        map.clear();
        map.equals();
        map.remove();
        map.clone();
        map.containsKey();
        map.containsValue();
        map.entrySet();
        map.isEmpty();
        map.put();
        map.replace();
        map.keySet();
        map.values();
        map.hashCode();
        map.toString();*/

        MyMap<String, String> map2 = new MyTreeMap<>();
        map2.put("key", "asd");
        System.out.println(map2);
    }
}
