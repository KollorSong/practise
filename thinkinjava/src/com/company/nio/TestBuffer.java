package com.company.nio;


import org.junit.Test;

import java.nio.ByteBuffer;

/*
* 缓冲区：用于存储不同数据类型的数组
*
* 根据数据类型不同，制定了不同类型的缓冲区
*
* ByteBuffer
*
* CharBuffer
*
* ShortBuffer
*
* IntBuffer
*
*LongBuffer
*
* FloadBuffer
*
* DoubleBuffer
*
* 上述缓冲区管理方式一致，通过allocate（）方法获取
*
* 缓冲区存取数据的连个核心方法
*
* put();存入数据到缓冲区
* get();获取缓冲区中的数据
*
* position：游标指针位置
* limt：极限限制
* capacity：容量
* mark:标记一个状态，可以用reset()进行回退
*
* 直接缓冲区和非直接缓冲区：
* 非直接缓冲区：通过allocate()方法，将缓冲区建立在JVM内存中。
* 直接缓冲区：通过allocateDirect()方法，将缓冲区建立到操作系统物理内存中，这就是效率提高的根本
*
*
*
*
*
*
*
*/
public class TestBuffer {


    @Test
    public void test3(){
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        System.out.println(byteBuffer.isDirect());
    }


    @Test
    public void test2(){

        String testStr = "abcde";

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        byteBuffer.put(testStr.getBytes());

        byteBuffer.flip();

        byte[] dst = new byte[byteBuffer.limit()];

        byteBuffer.get(dst,0,2);
        System.out.println(new String(dst,0,2));
        System.out.println(byteBuffer.position());

        byteBuffer.mark();
        System.out.println("================================");
        byteBuffer.get(dst,0,2);
        System.out.println(new String(dst,2,2));
        System.out.println(byteBuffer.position());

        System.out.println("===================================");
        System.out.println(new String(dst));

//        byteBuffer.reset();
//        System.out.println(byteBuffer.position());

        if (byteBuffer.hasRemaining()){  //是否含有剩余的可操作数据
            System.out.println("yes");
            System.out.println(byteBuffer.remaining());
        }




    }

    @Test
    public void test1(){

        String testStr = "abcde";

        //分配一个指定大小的缓冲区（此处是1024）
        ByteBuffer bb = ByteBuffer.allocate(1024);
        System.out.println("===============allocate()==================");
        System.out.println("capacity : "+bb.capacity());
        System.out.println("limt : "+bb.limit());
        System.out.println("position : "+bb.position());

        System.out.println("-----------------put()---------------------");
        bb.put(testStr.getBytes());
        System.out.println("capacity : "+bb.capacity());
        System.out.println("limt : "+bb.limit());
        System.out.println("position : "+bb.position());

        System.out.println("++++++++++++++++++flip()+++++++++++++++++++");
        bb.flip();
        System.out.println("capacity : "+bb.capacity());
        System.out.println("limt : "+bb.limit());
        System.out.println("position : "+bb.position());



    }
}
