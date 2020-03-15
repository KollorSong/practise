package cn.song.multithread;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Description: 线程范围内变量共享
 * @author: syq
 * @Date: 2020/3/15
 * @param:
 * @return:
 * @throws：
 */
public class ThreadScopeShareData {

    private static int data = 0;

    private static Map<Thread,Integer> threadData =
            new HashMap<Thread, Integer>();

    public static void main(String[] args) {

        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    data = new Random().nextInt();
                    Thread thread = Thread.currentThread();
                    threadData.put(thread,data);
                    System.out.println(thread.getName()+" has put data :" + data);
                    new A().aget();
                    new B().bget();
                }
            }).start();
        }

    }

    static class A{
        public void aget(){
            //System.out.println("from A "+ Thread.currentThread().getName() + " get data : " + data);
            Thread thread = Thread.currentThread();
            System.out.println("from A "+ thread.getName() + " get data : " + threadData.get(thread));
        }
    }


    static class B{
        public void bget(){
            //System.out.println("from B "+ Thread.currentThread().getName() + " get data : " + data);
            Thread thread = Thread.currentThread();
            System.out.println("from B "+ thread.getName() + " get data : " + threadData.get(thread));
        }
    }




}
