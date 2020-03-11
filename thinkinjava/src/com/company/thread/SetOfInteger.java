package com.company.thread;

import java.util.*;

public class SetOfInteger {

    public static void main(String[] args) {
//        Random random;
//        random = new Random();
//        HashSet<Integer> integers = new HashSet<>();
//        for (int i = 0; i < 1000; i++) {
//            integers.add(random.nextInt(30));
//        }
//        System.out.println(integers);


        List list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
            int i = (Integer) iterator.next();
            System.out.println(i);
        }


    }


}














































