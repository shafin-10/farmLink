package org.example;

import java.util.HashSet;
import java.util.Set;

public class SetOperationsDemo {
    public static void main(String[] args) {
        Set<Integer> hashSet1 = new HashSet<>();
        hashSet1.add(7);
        hashSet1.add(4);
        hashSet1.add(2);

        Set<Integer> hashSet2 = new HashSet<>();
        hashSet2.add(4);
       // hashSet2.add(5);
        hashSet2.add(2);

        //union
        /*hashSet1.addAll(hashSet2);
        System.out.println(hashSet1);*/

        //intersection
        /*hashSet1.retainAll(hashSet2);
        System.out.println(hashSet1);*/

        //removeAll
        /*hashSet1.removeAll(hashSet2);
        System.out.println(hashSet1);*/

        //if set2 is a subset of set1
        /*boolean output = hashSet1.containsAll(hashSet2);
        System.out.println(output);*/
    }
}
