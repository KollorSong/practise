package com.company.nio;


import java.io.FileInputStream;

/*
* 通道：用于源节点与目标节点的链接，
*
*
* */
public class TestChannel {



    public void test1() throws Exception{
        FileInputStream fileInputStream = new FileInputStream("1.jpg");
        //利用通道完成文件复制
    }

}
