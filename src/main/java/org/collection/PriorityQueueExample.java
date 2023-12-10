package org.collection;

import javax.sound.midi.Soundbank;
import java.util.Iterator;
import java.util.PriorityQueue;

public class PriorityQueueExample {
    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(10);
        pq.add(20);
        pq.add(4);
        pq.add(15);
        pq.add(1);
        pq.add(2);
        System.out.println(pq); // Order not maintained,  The insertion order is not retained in the PriorityQueue.
        //The elements are stored based on the priority order which is ascending by default.
        //We will not get sorted elements by printing PriorityQueue.
        System.out.println(pq.peek()); // Top element : 1
        System.out.println(pq.poll()); // print Top element and remove it
        System.out.println(pq);// Order not maintained
        pq.offer(6); // adding 6
        System.out.println(pq);// Order not maintained

        PriorityQueue<String> pqs = new PriorityQueue<>();

        pqs.add("Geeks");
        pqs.add("For");
        pqs.add("Geeks");  pqs.add("AGeeks");   pqs.add("CGeeks");

//we can access only the head of the queue.

        System.out.println(pqs);
        pqs.remove("Geeks");

/*        Iterating the PriorityQueue: There are multiple ways to iterate through the PriorityQueue.
                The most famous way is converting the queue to the array and traversing using the for loop.
                However, the queue also has an inbuilt iterator which can be used to iterate through the queue.*/
        Iterator iterator = pqs.iterator();

        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
    }
}
