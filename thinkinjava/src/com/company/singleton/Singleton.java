package com.company.singleton;




/**
 * @Description: 设计步骤：
 * 1.私有化构造方法
 * 2.创建类型变量
 * 3.提供类静态方法
 * @author: syq
 * @Date: 2020/3/14
 * @param:
 * @return:
 * @throws：
 */



// 饿汉式单例
class Singleton1 {

    // 指向自己实例的私有静态引用，主动创建
    private static Singleton1 singleton1 = new Singleton1();

    // 私有的构造方法
    private Singleton1(){}

    // 以自己实例为返回值的静态的公有方法，静态工厂方法
    public static Singleton1 getSingleton1(){
        return singleton1;
    }
}


// 懒汉式单例
class Singleton2 {

    // 指向自己实例的私有静态引用
    private static Singleton2 singleton2;

    // 私有的构造方法
    private Singleton2(){}

    // 以自己实例为返回值的静态的公有方法，静态工厂方法
    public static Singleton2 getSingleton2(){
        // 被动创建，在真正需要使用时才去创建
        if (singleton2 == null) {
            singleton2 = new Singleton2();
        }
        return singleton2;
    }
}


// 线程安全的懒汉式单例
//同步延迟加载 — synchronized方法
class Singleton3 {

    private static Singleton3 singleton3;

    private Singleton3(){}

    // 使用 synchronized 修饰，临界资源的同步互斥访问
    public static synchronized Singleton3 getSingleton3(){
        if (singleton3 == null) {
            singleton3 = new Singleton3();
        }
        return singleton3;
    }
}


// 线程安全的懒汉式单例
//同步延迟加载 — synchronized块
class Singleton4 {

    private static Singleton4 singleton4;

    private Singleton4(){}


    public static Singleton4 getSingleton4(){
        synchronized(Singleton4.class){  // 使用 synchronized 块，临界资源的同步互斥访问
            if (singleton4 == null) {
                singleton4 = new Singleton4();
            }
        }
        return singleton4;
    }
}







// 线程安全的懒汉式单例
//同步延迟加载 — 使用内部类实现延迟加载
class Singleton5 {

    // 私有内部类，按需加载，用时加载，也就是延迟加载
    private static class Holder {
        private static Singleton5 singleton5 = new Singleton5();
    }

    private Singleton5() {}

    public static Singleton5 getSingleton5() {
        return Holder.singleton5;
    }
}


// 线程安全的懒汉式单例
//）双重检测
class Singleton6 {

    //使用volatile关键字防止重排序，因为 new Instance()是一个非原子操作，可能创建一个不完整的实例
    private static volatile Singleton6 singleton6;

    private Singleton6(){}

    public static Singleton6 getSingleton6() {
        // Double-Check idiom
        if (singleton6 == null) {
            synchronized (Singleton6.class) {       // 1
                // 只需在第一次创建实例时才同步
                if (singleton6 == null) {       // 2
                    singleton6 = new Singleton6();      // 3
                }
            }
        }
        return singleton6;
    }
}


//ThreadLocal实现
class Singleton {

    // ThreadLocal 线程局部变量，将单例instance线程私有化
    private static ThreadLocal<Singleton> threadloca = new ThreadLocal<Singleton>();
    private static Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {

        // 第一次检查：若线程第一次访问，则进入if语句块；否则，若线程已经访问过，则直接返回ThreadLocal中的值
        if (threadloca.get() == null) {
            synchronized (Singleton.class) {
                if (instance == null) {  // 第二次检查：该单例是否被创建
                    instance = new Singleton();
                }
            }
            threadloca.set(instance); // 将单例放入ThreadLocal中
        }
        return threadloca.get();
    }
}


