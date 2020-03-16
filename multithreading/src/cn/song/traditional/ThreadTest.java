package cn.song.traditional;

import cn.song.deadlock.DeadLockRunnable;
import com.sun.org.apache.xerces.internal.util.EntityResolverWrapper;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class ThreadTest {


    public static void main(String[] args) {

        //方式一、继承事项多线程
//        Thread thread1 = new MyThread();
//        thread1.start();

        //方式二、实现Runnable接口方式
//        Thread thread = new Thread(new MyRunnable());
//        thread.start();

        //方式三、事项Callable接口方式
//        FutureTask<String> task = new FutureTask(new MyCallable());
//
//        Thread thread = new Thread(task);
//        thread.start();
//        //主线程中之执行的结果
//        for (int i = 0; i < 10; i++) {
//            System.out.println(Thread.currentThread().getName() + " : Main 执行了，第 "+i+" 次");
//        };

        //获取Callable接口返回的结果（加到这里，则顺序控制，先执行此线程，然后再顺序执行）
//        try {
//            String result = task.get();
//            System.out.println(result);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

//        //方式四、线程池方式
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        for (int i = 0; i < 2; i++) {
//            executorService.execute(new MyRunnable());
//        }
//
//        //主线程中之执行的结果
//        for (int i = 0; i < 10; i++) {
//            System.out.println(Thread.currentThread().getName() + " : Main 执行了，第 "+i+" 次");
//        };



        //测试售票
//        Tictket ticket = new Tictket();
//        Thread thread1 = new Thread(ticket, "窗口1");
//        Thread thread2 = new Thread(ticket, "窗口2");
//        Thread thread3 = new Thread(ticket, "窗口3");
//
//        thread1.start();
//        thread2.start();
//        thread3.start();


        //死锁
        DeadLockRunnable r1 = new DeadLockRunnable(false);
        DeadLockRunnable r2 = new DeadLockRunnable(true);

        new Thread(r1).start();
        new Thread(r2).start();

    }
}
