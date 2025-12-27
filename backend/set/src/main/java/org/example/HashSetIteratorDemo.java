package org.example;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HashSetIteratorDemo {
    public static void main(String[] args) {
        Set<Integer> nums = new HashSet<>();

        nums.add(1);
        nums.add(3);
        nums.add(2);
        nums.add(100);
        nums.add(20);
        nums.remove(20);
        for(var it : nums){
            System.out.println(it);
        }

        Iterator<Integer> iterator = nums.iterator();
        while (iterator.hasNext()){
            Integer num = iterator.next();
            System.out.println(num);
        }
    }
}
