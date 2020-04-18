package com.company.nio;


import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/*
* 一、通道：用于源节点与目标节点的链接，Channel本身并不存储数据，因此要配合缓冲区进行传输
*
* 二、通道的主要实现：
*   java.nio.channels.Channel 接口：
*       |--FileChannel
*       |--SocketChannel
*       |--ServerSocketChannel
*       |--DatagaraChannel
*
* 三、通道获取
*   1、java针对通道类提供了getChannel()方法
*       本地IO:
*           FileInputStream/FileOutputStream
*           RandomAccessFile
*       网络IO:
*           Socket
*           ServerSocket
*           DatagramSocket
*   2、在JDK 1.7 中的 NIO.2 针对各个通道提供了静态方法open()
*
*   3、在JDK 1.7 中的 NIO.2 的 Files 工具类newByteChannel()
*
*
*
*
* */
public class TestChannel {



    @Test
    public void test1() {

        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;

        FileChannel inChannel = null;
        FileChannel outChannel = null;

        try {

            fileInputStream = new FileInputStream("1.jpg");
            fileOutputStream= new FileOutputStream("2.jpg");
            //获取通道
            inChannel = fileInputStream.getChannel();
            outChannel = fileOutputStream.getChannel();

            //分配制定大小缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            //通道中的数据写入缓冲区
            int x = 0;
            while ((x = inChannel.read(buffer)) != -1 ){
                //将缓冲区中的数据写入通道中
                buffer.flip(); //读数据模式
                outChannel.write(buffer);
                buffer.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (outChannel != null){
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inChannel != null){
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
