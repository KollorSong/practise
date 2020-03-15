package cn.song.synchronizeddemo;

public class TraditionalThreadSynchronized {


    public static void main(String[] args) {
        printOut("zhangxiaoxiang");
        printOut("liguoming");

    }

    static void printOut(String name) {
        OutPuter outPuter = new OutPuter();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    outPuter.output(name);
                }
            }
        }.start();
    }


    static class OutPuter {
//        public void output(String name) {
//            synchronized (this) {
//                try {
//                    if (name != null || name.length() != 0) {
//                        int len = name.length();
//                        for (int i = 0; i < len; i++) {
//                            System.out.print(name.charAt(i));
//                        }
//                        System.out.println();
//                    } else {
//                        throw new Exception("zifuchuanweikogn");
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }

        public synchronized void output(String name) {
            try {
                if (name != null || name.length() != 0) {
                    int len = name.length();
                    for (int i = 0; i < len; i++) {
                        System.out.print(name.charAt(i));
                    }
                    System.out.println();
                }else {
                    throw new Exception("zifuchuanweikogn");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
