package org.example;

import java.util.TreeSet;

public class TreeSetDemo {
    public static void main(String[] args) {
        TreeSet<Integer> treeSet = new TreeSet<>();

        treeSet.add(100);
        treeSet.add(20);
        treeSet.add(50);
        treeSet.add(25);
        System.out.println("Treeset : " + treeSet);

        treeSet.remove(20);
        System.out.println("Treeset after removal : " + treeSet);

    }
}
