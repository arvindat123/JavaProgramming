package org.designpattern.singleton;


class SingletonClass1 {
    private SingletonClass1(){
        System.out.println("Object made");
    }
    private static class NestedClass {
        static SingletonClass1 instance = new SingletonClass1();
    }
    public static SingletonClass1 getInstance(){
        return NestedClass.instance;
    }
}

public class BillPughImplementation {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                SingletonClass1 obj1 = SingletonClass1.getInstance();
                System.out.println(obj1.hashCode());
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                SingletonClass1 obj2 = SingletonClass1.getInstance();
               System.out.println(obj2.hashCode());
            }
        });
        t1.start();
        t2.start();
    }
}
