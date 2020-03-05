# Java学习路线
## Java基础课程
### 第一部分：Java开发介绍

1. DOS常用命令  
    + d:+ 回车：盘符切换，进入D:盘  
    + dir(directory)：列出当前目录下的文件及文件夹  
    + md(make director)：创建目录  
    + rd(remove director)：删除目录（不能删除非空的文件夹）  
    + rd + /s 文件夹名称 (询问是否删除)  
    + rd + /q + /s 文件夹名称 (直接删除)  
    + cd(change directory)：改变指定目录(进入指定目录)  
    + cd..：退回到上一级目录  
    + cd\：退回到根目录  
    + del(delete)：删除文件，也可以删除一堆后缀名一样的文件(例如*.txt)  
    + exit：退出dos命令行  
    + cls(clear screen)：清屏  
    + ren：重命名
    + copy：复制文件  
    ```
    D:>copy test.txt temp → 如果test.txt文件和temp目录都在D盘根目录，不要写全目录
    ​D:>copy C:\test.txt C:\temp  → 如果text.txt和temp目录都不在D盘根目录，那么要写全目录
    ```
    + edit：打开命令行窗口的快速编辑功能(Win7 64位及之后版本没有此功能)
    + notepad：打开记事本
    + Java中常用的DOS命令：
    + javac: 编译代码（即将.java文件编译成.class的中立字节码文件）。使用举例： javac HelloDos.java
    + java：解释代码。使用举例： java HelloDos（注意这个地方不需要.class后缀）
    + javap：反编译，也可查看Java编译器生成的字节码。使用举例：javap HelloDos（注意这里不需要.class后  缀）
    + javadoc：生成文档。使用举例： 
    ```
    javadoc HelloDos.java
    ```

    + javac: 编译命令
        1. 编译一个文件
            ```
            javac xxx.java
            ```
        2. 编译多个源文件：
            1. 如果文件都在同一目录 
                ```
                    javac HelloDos*.java 或者 javac *.java
                ```
            2. 如果文件不在同一目录,假设有三个文件 HelloDos1.java、HelloDos2.java、HelloDos3.java 分别在1、2、3这三个文件夹里面，那么可以在D:\temp先建一个文件FilesList.txt，然后在此文件里面添加如下内容：
                ```
                            D:\temp\1\HelloDos1.java
                ​            D:\temp\2\HelloDos2.java
                            D:\temp\3\HelloDos3.java 
                ```
                然后直接输入命令```javac @FilesList.txt```将.java文件和.class分开。
                从上面的例子可以看出，如果直接用javac HelloDos.java，则编译后的HelloDos.class和HelloDos.java在同一目录下，那么，怎么让它们不在同一目录下呢？假如D:\test下有三个文件夹：src、lib和classes，其中src下有HelloDos1.java和HelloDos2.java，lib  下有Banner.jar，classses下没有文件。我们可以通过以下命令编译java文件并将编译好的.classes文件放入  classes中。
                ```
                   javac -sourcepath src -classpath classes:lib\Banners.jar src\*.java -d classes
                ```  
      

2.  JVM、JRE、JDK之间的关系


### 第二部分：Java数组
### 第三部分：Java面向对象
### 第四部分：常用基础类
### 第五部分：集合
### 第六部分：IO流
### 第七部分：多线程
### 第八部分：异常
### 第九部分：网络
### 第十部分：反射

## JavaWeb课程
## Java框架课程
## Java+云数据课程
