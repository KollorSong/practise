package cn.song.traditional;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TraditionalTimer {


    private static int x = 0;


    public static void main(String[] args) {
//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("bombing");
//            }
//        },1000,3000);
//        while (true){
//            System.out.println(new Date().getSeconds());
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }


        class MyTimerTask extends TimerTask{
            @Override
            public void run() {
                x = (++x)%2;

                System.out.println("bombing: " + x);
                new Timer().schedule(/*new TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("bombing");
                    }
                }*/new MyTimerTask(), 2000+2000*x);

            }
        };

        new Timer().schedule(new MyTimerTask(),5000);
        while (true) {
            System.out.println(new Date().getSeconds());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
