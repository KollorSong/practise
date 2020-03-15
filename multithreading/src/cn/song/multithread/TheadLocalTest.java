package cn.song.multithread;

/**
 * @Description: ThreadLocal实现线程内变量共享
 * 1.
 * @author: syq
 * @Date: 2020/3/15
 * @param:
 * @return:
 * @throws：
 */
public class TheadLocalTest {

    //  private  static ThreadLocal<MyThreadScopeData> x = new ThreadLocal<MyThreadScopeData>();
//    private  static ThreadLocal<Integer> x = new ThreadLocal<Integer>();

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    new A().get();
                    new B().get();
                }
            }).start();

            Thread thread = new Thread();


        }
    }


    static class A {
        public void get() {
            MyThreadScopeData.getThreadInstance().setName("kakak");
            MyThreadScopeData.getThreadInstance().setAge(12);
            System.out.println("By A " + Thread.currentThread().getName() + " has reverse data : " + MyThreadScopeData.getThreadInstance());
            System.out.println("from A " + Thread.currentThread().getName() + " get data : " + MyThreadScopeData.getThreadInstance());
        }
    }


    static class B {
        public void get() {
            MyThreadScopeData.getThreadInstance().setName("xixixi");
            MyThreadScopeData.getThreadInstance().setAge(15);
            System.out.println("By B " + Thread.currentThread().getName() + " has reverse data : " + MyThreadScopeData.getThreadInstance());
            System.out.println("from B " + Thread.currentThread().getName() + " get data : " + MyThreadScopeData.getThreadInstance());
        }
    }
}

class MyThreadScopeData {

    private String name;
    private int age;

    private static ThreadLocal<MyThreadScopeData> threadScopeDataThreadLocal = new ThreadLocal<>();

    private MyThreadScopeData() {

    }

    public static MyThreadScopeData getThreadInstance() {
        MyThreadScopeData myThreadScopeData = threadScopeDataThreadLocal.get();
        if (myThreadScopeData == null) {
            myThreadScopeData = new MyThreadScopeData();
            threadScopeDataThreadLocal.set(myThreadScopeData);
            System.out.println(Thread.currentThread().getName() + " has put data : " + myThreadScopeData);
        }
        return myThreadScopeData;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}
