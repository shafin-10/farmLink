package org.example;

import java.util.LinkedHashSet;
import java.util.LinkedList;

public class LinkedHashSetDemo {
    public static void main(String[] args) {

        LinkedHashSet<Integer> linkedSet = new LinkedHashSet<>();
        linkedSet.add(100);
        linkedSet.add(20);
        linkedSet.add(50);
        linkedSet.add(25);
        System.out.println("linked Set : " + linkedSet);
    }
}
