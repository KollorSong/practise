package cn.song.traditional;

public class TraditionalThreadConnection {


    public static void main(String[] args) {

        final Business business = new Business();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    business.sub(i+1);
                }

            }
        }).start();

        for (int i = 0; i < 10; i++) {
            business.main(i+1);
        }

    }
}

class Business {

    //子线程等待，主线程执行
    private boolean flag = true;

    public synchronized void sub(int i) {
        if (flag) {
            try {
                System.out.println("子线程等待");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("子线程开始干活");
        for (int j = 0; j < 5; j++) {
            System.out.println("sub thread sequece of " + j + ", loop of " + i);
        }
        flag = true;
        this.notifyAll();
    }

    public synchronized void main(int i) {
        if (!flag) {
            try {
                System.out.println("主线程等待");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("主线程开始干活");
        for (int j = 0; j < 5; j++) {
            System.out.println("main thread sequece of " + j + ", loop of " + i);
        }
        flag = false;
        this.notifyAll();
    }

}



















































