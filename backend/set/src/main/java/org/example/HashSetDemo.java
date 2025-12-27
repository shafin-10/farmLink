package org.example;

import java.util.HashSet;
import java.util.Set;

public class HashSetDemo {
    public static void main(String[] args) {
        Set<String> visitedCountries = new HashSet<>();
        visitedCountries.add("Usa");
        visitedCountries.add("Dubai");
        visitedCountries.add("Canada");
        visitedCountries.add("India");

        for(var it : visitedCountries)
            System.out.println(it);

    }
}
