package com.company.nio;


import java.io.FileInputStream;

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



    public void test1() throws Exception{
        FileInputStream fileInputStream = new FileInputStream("1.jpg");
        //利用通道完成文件复制
    }

}
