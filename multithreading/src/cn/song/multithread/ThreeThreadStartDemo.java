package cn.song.multithread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ThreeThreadStartDemo {
    private CyclicBarrier cyclicBarrier =
            new CyclicBarrier(3);

    public void startThread(){
        //1.获取线程名称
        String name = Thread.currentThread().getName();
        System.out.println(name +  " 正在准备");
        //2.调用await方法，让线程全部准备
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        //3.线程启动完毕，打印信息
        System.out.println(name + " 启动完毕，完成时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));


    }

    public static void main(String[] args) {

        ThreeThreadStartDemo threeThreadStartDemo = new ThreeThreadStartDemo();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                threeThreadStartDemo.startThread();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                threeThreadStartDemo.startThread();
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                threeThreadStartDemo.startThread();
            }
        });

        t1.start();
        t2.start();
        t3.start();

    }
}
