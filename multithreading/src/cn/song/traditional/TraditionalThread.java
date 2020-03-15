package cn.song.traditional;

public class TraditionalThread {
    public static void main(String[] args) {

        //重写Thread类中的run方法
        Thread thread = new Thread(){
            @Override
            public void run() {
                while (true){
                    //获取当前的线程
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    System.out.println(Thread.currentThread());
                    System.out.println(this.getName());
                }
            }
        };
        thread.start();


        //重写Runnable接口 的方法
        //此方法更能体现面向对象的思想
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    //获取当前的线程
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread());
                    //此时的this代表runable类，所以没法获取到当前线程
                    //System.out.println(this.getName());
                }
            }
        });
        thread1.start();




        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    //获取当前的线程
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("runable: "+Thread.currentThread());
                }
            }
        }){
            @Override
            public void run() {
                while (true) {
                    //获取当前的线程
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("thread: "+Thread.currentThread());
                }
            }
        }.start();





    }
}
