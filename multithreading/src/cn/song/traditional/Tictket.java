package cn.song.traditional;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Tictket implements Runnable {

    private int ticktNum = 100;

    private Object object = new Object();

    //同步锁
    Lock lock = new ReentrantLock(true);//true-公共锁（所有线程都公平拥有执行权）、false-非公平锁（）

    //进行一次售票操作
    public void run() {
        while (true) {
            //一、同步代码快的方式实现线程安全
//            synchronized (object){
//                if (ticktNum > 0) {
//                    //1.模拟出票时间
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    //2.打印进程号和票号，票数减1
//                    String name = Thread.currentThread().getName();
//                    System.out.println("线程" + name + "售票：" + ticktNum--);
//                }
//            }

            //二、同步方法实现线程安全
            //saleTicket();

            //三、同步锁方式
            while (true) {
                lock.lock();
                try {
                    if (ticktNum > 0) {
                        //1.模拟出票时间
                        Thread.sleep(100);
                        //2.打印进程号和票号，票数减1
                        String name = Thread.currentThread().getName();
                        System.out.println("线程" + name + "售票：" + ticktNum--);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }


        }
    }


    /**
     * 同步方法的实现：
     * 如果方法是静态方法，原理是加的类的class
     * 如果是普通方法，相当于锁的this，调用者
     */
    public synchronized void saleTicket() {
        if (ticktNum > 0) {
            //1.模拟出票时间
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //2.打印进程号和票号，票数减1
            String name = Thread.currentThread().getName();
            System.out.println("线程" + name + "售票：" + ticktNum--);
        } else {
            return;
        }
    }
}



















