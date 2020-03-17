package cn.song.multithread;

import java.util.concurrent.Semaphore;

public class WorkMachineDemo {

    static class Worker implements Runnable{

        private int workerNo;//工人编号
        private Semaphore semaphore; //操作的机器资源

        public Worker(int workerNo, Semaphore semaphore) {
            this.workerNo = workerNo;
            this.semaphore = semaphore;
        }


        @Override
        public void run() {

            try {

                //1.工人要去获取机器
                semaphore.acquire();

                //2.打印工人获取机器，开始工作
                String name = Thread.currentThread().getName();
                System.out.println(name+ " 工人获取到机器，开始工作。。。");

                //3.用线程睡眠代表工人使用机器
                Thread.sleep(3000);

                //4.释放机器
                semaphore.release();
                System.out.println(name + " 使用完毕，释放完成");


            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }


    public static void main(String[] args) {
        int workers = 8; //8个工人
        Semaphore semaphore = new Semaphore(3); // 3台机器
        for (int i = 0; i < workers; i++) {
            new Thread(new Worker(i,semaphore)).start();
        }
    }

}
