package cn.song.multithread;

public class LiftOff  implements Runnable{

    protected int count = 10;
    private static int taskCount = 0;
    private final int id = taskCount++;

    public LiftOff(int count){
        this.count = count;
    }

    public LiftOff(){
    }


    public String status(){
        return "# "+id +  " ("+ (count>0?count:"liftoff") +" ).";
    }

    @Override
    public void run() {
        String s="";
        synchronized (s){
            while (count-->0) {
                System.out.println(status());
                Thread.yield();
            }
            System.out.println("============================");
        }

    }
}
