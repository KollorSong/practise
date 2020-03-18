package cn.song.multithread;


/**
 * @Description: 设置一个共有变量传入，对同一变量进行操作
 * @author: syq
 * @Date: 2020/3/16
 * @param:
 * @return:
 * @throws：
 */

public class PCModel {

    ThreadLocal<PublicBox> boxThreadLocal = new ThreadLocal<>();

    public static void main(String[] args) {

        PublicBox box = new PublicBox();

        Consumer con = new Consumer(box);
        Producer pro = new Producer(box);

        Thread t1 = new Thread(con);
        Thread t2 = new Thread(pro);

        t1.start();
        t2.start();

    }

}


class PublicBox {

    private int apple = 0;


    public int getApple() {
        return apple;
    }

    public synchronized void increace() {
        while (apple == 5) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        apple++;
        System.out.println("生成苹果成功！");
        notify();
    }

    public synchronized void decreace() {
        while (apple == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        apple--;
        System.out.println("消费苹果成功！");
        notify();
    }


}

//生产者代码（定义十次）：
class Producer implements Runnable {

    private PublicBox box;

    public Producer(PublicBox box) {
        this.box = box;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("pro  i:" + i);
                System.out.println("目前剩余：" + box.getApple()+" 个");
                Thread.sleep(30);
            } catch (InterruptedException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
            box.increace();
        }
    }

}

//        消费者代码（同样十次）：

class Consumer implements Runnable {
    private PublicBox box;

    public Consumer(PublicBox box) {
        this.box = box;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("Con: i " + i);
                System.out.println("目前剩余：" + box.getApple()+" 个");
                Thread.sleep(3000);//这里设置跟上面30不同是为了 盒子中的苹果能够增加，不会生产一个马上被消费  
            } catch (InterruptedException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
            box.decreace();
        }
    }
}
