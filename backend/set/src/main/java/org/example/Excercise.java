package org.example;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Excercise {
    public static void main(String[] args) {
        Set<String> fruits = new HashSet<>();
        fruits.add("apple");
        fruits.add("banana");
        fruits.add("orange");
        fruits.add("lynx");
        fruits.add("grape");

        Iterator<String> iterator = fruits.iterator();
        while (iterator.hasNext()){
            String fruit = iterator.next();
            if (fruit.matches(".*[aeiouAEIOU].*")){
                iterator.remove();
            }
        }

        //return fruits;
    }
}
