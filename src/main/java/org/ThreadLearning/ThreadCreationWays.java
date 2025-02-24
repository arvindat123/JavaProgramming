package org.ThreadLearning;

//1 way by implementing Runnable interface
/*public class ThreadCreationWays implements Runnable{
    @Override
    public void run(){
        System.out.println("Way 1 for T1 : By implementing Runnable interface");
    }
    public static void main(String[] args) {
        ThreadCreationWays object = new ThreadCreationWays();
        Thread T1 = new Thread(object);
        T1.start();
    }
}*/

//2 Way by extending Thread class, thread class implements Runnable interface and override run method
public class ThreadCreationWays extends Thread {
    @Override
    public void run(){
        System.out.println("Way 2 for T2 : By extending Thread class");
    }
    public static void main(String[] args) {
        ThreadCreationWays object = new ThreadCreationWays();
        Thread T1 = new Thread(object);
        T1.start();

        //Way 3
        Thread T3 = new Thread(() -> {
            System.out.println("Way 3 for T3 : By Lambda expression");
        });
        T3.start();

        //Way 4
        Thread T4 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Way 4 for T4 : By Lambda expression with Runnable interface");
            }
        });
        T4.start();
    }
}
