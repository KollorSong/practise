package cn.song.traditional;

public class MyThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " : MyThread 执行了，第 "+i+" 次");
        };
    }
}
