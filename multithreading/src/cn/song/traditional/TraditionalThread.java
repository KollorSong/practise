package cn.song.traditional;

public class TraditionalThread {
    public static void main(String[] args) {


        Thread thread = new Thread(){

            @Override
            public void run() {

                while (true){

                    try {
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        };

        thread.start();


    }
}
