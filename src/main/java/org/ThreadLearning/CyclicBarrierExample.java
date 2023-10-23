package org.ThreadLearning;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
    private static final int NUM_THREADS = 3;

    public static void main(String[] args) {
        // Creating a CyclicBarrier with a runnable to be executed when the barrier is tripped
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUM_THREADS, () -> {
            System.out.println("Barrier is tripped. All threads can proceed.");
        });

        for (int i = 0; i < NUM_THREADS; i++) {
            Thread thread = new Thread(new Worker(i, cyclicBarrier));
            thread.start();
        }
    }

    static class Worker implements Runnable {
        private final int id;
        private final CyclicBarrier cyclicBarrier;

        Worker(int id, CyclicBarrier cyclicBarrier) {
            this.id = id;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                System.out.println("Thread " + id + " is doing some work.");
                // Simulating some work by sleeping for a random time
                Thread.sleep((long) (Math.random() * 3000));
                System.out.println("Thread " + id + " has finished its work and waiting at the barrier.");

                // All threads will wait here until the specified number of threads (NUM_THREADS) reach this point
                cyclicBarrier.await();

                System.out.println("Thread " + id + " is now continuing its execution.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}