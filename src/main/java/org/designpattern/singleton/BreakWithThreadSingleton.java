package org.designpattern.singleton;

class SingletonPattern {

    private static SingletonPattern instance;

    private SingletonPattern(){
     //   System.out.println("Object made");
    }

    public static SingletonPattern getInstance(){
        if(instance == null){
            instance = new SingletonPattern();
        }
        return instance;
    }
}

public class BreakWithThreadSingleton{
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                SingletonPattern obj1 = SingletonPattern.getInstance();
                System.out.println(obj1.hashCode());
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                SingletonPattern obj2 = SingletonPattern.getInstance();
                System.out.println(obj2.hashCode());
            }
        });
        t1.start();
        t2.start();

       /* System.out.println(obj1);
        System.out.println(obj2);
        if (obj1 == obj2){
            System.out.println("Memory address are same");
        }
        if(obj1.equals(obj2)){
            System.out.println("Objects are equals");
        }*/
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}



