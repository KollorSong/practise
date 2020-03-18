package cn.song.traditional;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " : MyCallable 执行了");
        return "返回 MyCallable 执行结果";
    }
}
