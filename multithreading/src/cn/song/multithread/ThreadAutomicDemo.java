package cn.song.multithread;

import java.util.concurrent.atomic.AtomicStampedReference;

public class ThreadAutomicDemo {

//    static int n =0;
//    static AtomicInteger atomicInteger;

    static AtomicStampedReference<Integer> atomicInteger;


    public static void main(String[] args) throws Exception{

        for (int i = 0; i < 100; i++) {
            //n = 0;
            //atomicInteger = new AtomicInteger(0);//创建原子整数，设立初始值为0
            atomicInteger= new AtomicStampedReference(0,0);  //创建原始操作类

            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        //++n;
                        //atomicInteger.getAndIncrement();//进行一次自加，对应 n++
                        int stam;
                        Integer reference;
                        do {
                            stam = atomicInteger.getStamp();
                            reference = atomicInteger.getReference();

                        }while (!atomicInteger.compareAndSet(reference,reference+1,stam,stam+1));
                    }
                }
            });
            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        //++n;
                        //atomicInteger.getAndIncrement();//进行一次自加，对应 n++
                        int stam;
                        Integer reference;
                        do {
                            stam = atomicInteger.getStamp();
                            reference = atomicInteger.getReference();

                        }while (!atomicInteger.compareAndSet(reference,reference+1,stam,stam+1));
                    }
                }
            });

            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();

            //System.out.println("执行完毕，结果是：" + n);
            //System.out.println("执行完毕，结果是：" + atomicInteger.get());
            System.out.println("执行完毕，结果是：" + atomicInteger.getReference());
        }
    }
}
