package cn.song.deadlock;

public class DeadLockRunnable implements Runnable {

    private boolean flag ;//决定标记走向

    private static Object object1 = new Object();
    private static Object object2 = new Object();

    public DeadLockRunnable(boolean flag){
        this.flag = flag;
    }


    @Override
    public void run() {
        if (flag){
            //线程1之心代码
            synchronized (object1){
                System.out.println(Thread.currentThread().getName()+"已经获取到资源1，正在请求资源2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object2){
                    System.out.println(Thread.currentThread().getName()+"已经获取到资源2，正在请求资源1");
                }
            }
        }else {
            //线程2执行代码
            synchronized (object2){
                System.out.println(Thread.currentThread().getName()+"已经获取到资源2，正在请求资源1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object1){
                    System.out.println(Thread.currentThread().getName()+"已经获取到资源1，正在请求资源2");
                }
            }
        }

    }
}
