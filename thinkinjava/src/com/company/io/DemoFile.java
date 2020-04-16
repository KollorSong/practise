package com.company.io;

import org.junit.Test;

import java.io.File;

/*
* 路径：
*   相对路径：
*   绝对路径：一个完整的路径，以一个盘符开始
*
*
* 注意：
*   路径不区分大小写
*
* */

public class DemoFile {

    @Test
    public void test1() {
        String pathSeparator = File.pathSeparator;
        System.out.println(pathSeparator);
        String separator = File.separator;
        System.out.println(separator);
    }


    @Test
    public void testStructor(){
        show01();;
    }


    /*
    *
    *
    *
    * */
    private static void show01(){
        File file1 = new File("D:\\ideaworkspace\\1.txt");
        System.out.println(file1);
        System.out.println(file1.exists());
        System.out.println("===========================");

        File file2 = new File("D:\\ideaworkspace");
        System.out.println(file2);
        System.out.println(file2.exists());
        System.out.println("===========================");

        File file3 = new File("b.txt");
        System.out.println(file3);
        System.out.println(file3.exists());
        System.out.println("===========================");
    }



}
