package com.company;

import java.util.HashSet;
import java.util.Random;

public class SetOfInteger {
    public static void main(String[] args) {
        Random random;
        random = new Random();
        HashSet<Integer> integers = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            integers.add(random.nextInt(30));
        }
        System.out.println(integers);
    }
}
