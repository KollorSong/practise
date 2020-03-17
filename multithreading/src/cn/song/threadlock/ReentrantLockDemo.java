package cn.song.threadlock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    public static void main(String[] args) {

        ReentrantLock reentrantLock = new ReentrantLock();

        for (int i = 0; i < 10; i++) {
            reentrantLock.lock();
            System.out.println("枷锁次数： " + (i + 1));
        }

        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("jie锁次数： " + (i + 1));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        }

    }
}
