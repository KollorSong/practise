package cn.song.multithread;

public class MutiThreadShareData {

    public static void main(String[] args) {

        final ShareData shareData = new ShareData();

        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    shareData.decrement();
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    shareData.increment();
                }
            }).start();
        }

        System.out.println("++++++++++++++++++");
        System.out.println(shareData.getJ());
        System.out.println("++++++++++++++++++");

    }
}

class ShareData {

    private int j = 0;

    public synchronized void increment() {

        j++;
        System.out.println(Thread.currentThread().getName() + " 增加一次");
        System.out.println(j);
        System.out.println("============");
    }

    public synchronized void decrement() {
        j--;
        System.out.println(Thread.currentThread().getName() + " 减小一次");
        System.out.println(j);
        System.out.println("============");
    }

    public int getJ() {
        return j;
    }


}
