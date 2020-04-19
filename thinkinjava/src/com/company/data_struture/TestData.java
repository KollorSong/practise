package com.company.data_struture;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;

public class TestData {

    public static DoubleIcreatLinkList<String> dl;


    @Before
    public void init() {
        dl = new DoubleIcreatLinkList<String>();
        dl.insertToHead("N1");
        dl.insertTotatil("N2");
        dl.insertTotatil("N3");
        dl.insertTotatil("N4");
        dl.insertTotatil("N5");
        dl.insertTotatil("N6");
        dl.insertTotatil("N7");
    }


    @Test
    public void testDL() {

        showList(dl);

        System.out.println("==============================");
        dl.insertToHead("N0");
        dl.insertTotatil("N8");
        showList(dl);

        System.out.println("+++++++++++ 删除 0 ++++++++++++++");
        dl.del(3);
        dl.del(0);
        showList(dl);

        System.out.println("===================================");
        dl.delFirst();
        dl.delLast();

        showList(dl);
    }

    public void showList(DoubleIcreatLinkList<String> kaka){
        System.out.println(kaka.getSize());
        for (int i = 0; i < kaka.getSize(); i++) {
            System.out.println(kaka.getNode(i).getData());
        }
    }

    @Test
    public void test2(){
        System.out.println(5/2);
    }

    public void testLinkList(){
        LinkedList linkedList = new LinkedList();
        linkedList.get(0);
    }

}
