package cn.song.threadlock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadAndWriteDemo {
    //一个全局变量
    private Map<String, String> map = new HashMap<>();
    //获取读写锁
    private ReentrantReadWriteLock reentrantReadWriteLock =
            new ReentrantReadWriteLock();
    //获取读操作锁
    private ReentrantReadWriteLock.ReadLock readLock =
            reentrantReadWriteLock.readLock();
    //获取写操作锁
    private ReentrantReadWriteLock.WriteLock writeLock =
            reentrantReadWriteLock.writeLock();


    public String get(String key) {
        String name = Thread.currentThread().getName();
        readLock.lock();
        System.out.println(name + " : 读锁已经加锁，开始读取。。。");
        try {
            Thread.sleep(1000);
            return map.get(key);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return map.get(key);
        } finally {
            readLock.unlock();
            System.out.println(name + " : 读锁已经解锁。。。");
        }
    }

    public void put(String key, String value) {
        //获取线程名
        String name = Thread.currentThread().getName();
        writeLock.lock();
        System.out.println(name + " : 写锁已经加锁，开写入。。。");
        try {
            map.put(key, value);
        } finally {
            writeLock.unlock();
            System.out.println(name + " : 写锁已经解锁。。。");
        }


    }


    public static void main(String[] args) throws InterruptedException {

        ReadAndWriteDemo readAndWriteDemo = new ReadAndWriteDemo();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                readAndWriteDemo.put("key1", "value1");
            }
        });
        thread.start();
        thread.join();
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " : " + readAndWriteDemo.get("key1"));
                }
            }).start();
        }


    }


}
