package org.ThreadLearning;

public class VirtualThread {

    //Virtual threads are Java threads that are implemented by the Java runtime rather than the OS.
   // The main difference between virtual threads and the traditional threads—which we've come to call platform threads—is
    //that we can easily have a great many active virtual threads, even millions, running in the same Java process.
        public static void main(String[] args) {
          /*  for (int i = 0; i < 5; i++) {
                Thread virtualThread = Thread.ofVirtual().start(() -> {
                    System.out.println("Virtual Thread: " + Thread.currentThread().getName());
                });

                try {
                    virtualThread.join(); // Wait for the virtual thread to complete
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }*/

           // System.out.println("Main program exiting");
        }
}
