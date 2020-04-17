package com.company.thread;

import org.junit.Test;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class TestClass {

    @Test
    public void testproperties(){
        Properties properties = System.getProperties();
        Set<Map.Entry<Object, Object>> entries = properties.entrySet();
        for (Map.Entry entry : entries){
            System.out.println(entry.getKey()+" : "+ entry.getValue());
        }
    }






}
