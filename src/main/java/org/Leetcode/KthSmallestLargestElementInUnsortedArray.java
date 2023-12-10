package org.Leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KthSmallestLargestElementInUnsortedArray {
    //https://www.geeksforgeeks.org/kth-smallest-largest-element-in-unsorted-array/
   /* Given an array arr[] of size N and a number K, where K is smaller than the size of the array.
    Find the K’th smallest element in the given array. Given that all array elements are distinct.

    Input: arr[] = {7, 10, 4, 3, 20, 15}, K = 3
    Output: 7

    Input: arr[] = {7, 10, 4, 3, 20, 15}, K = 4
    Output: 10
    */
    //Sorting Technique
    public static int sortingTechnique(int[] arr, int K){
        Arrays.sort(arr);  // O(NlogN)-> Time complexity, O(1) -> Space complexity
        return arr[K-1];
    }

    //Binary Search Techniques
    /*public static int binarySearchTechnique(int[] arr, int K){
        int low = Integer.MAX_VALUE; //2147483647
        int high = Integer.MIN_VALUE; //-2147483648
        //Calculate min and max of array
        for(int i=0;i<arr.length;i++){
            low = Math.min(low,arr[i]);
            high = Math.max(high, arr[i]);

        }
        while(low < high){
            int mid = low + (high - low)/2;

        }
        return 0;
    }*/

    //Priority Queue (Max - heap)
/*    The intuition behind this approach is to maintain a max heap (priority queue) of size K while iterating through
    the array. Doing this ensures that the max heap always contains the K smallest elements encountered so far.
    If the size of the max heap exceeds K, remove the largest element this step ensures that the heap maintains
    the K smallest elements encountered so far. In the end, the max heap’s top element will be the Kth smallest element.
    Time Complexity: O(N * log(K)), The approach efficiently maintains a container of the K smallest elements while iterating through the array, ensuring a time complexity of O(N * log(K)), where N is the number of elements in the array.
    Auxiliary Space: O(K)
    */

    public static int priorityQueueTechnique(int[] arr, int K){
        int lengthOfArray = arr.length;

        //Creating max heap priority queue
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b - a);

        //Iterate through the array elements
        for(int i=0;i<arr.length;i++){
            // Push the current element onto the max heap
            pq.offer(arr[i]);

            // If the size of the max heap exceeds K, remove the largest element
            if(pq.size() > K){
                pq.poll();
            }

        }
        return pq.peek();//Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.

    }
    public static void main(String[] args) {
        int[] arr = new int[]{7, 10, 4, 3, 20, 15,3};
        int K = 3;
        System.out.println("sortingTechnique ==> "+sortingTechnique(arr,K));
       // System.out.println("Binary Search Techniques ==> "+binarySearchTechnique(arr,K));
        System.out.println("priorityQueueTechnique ==> "+priorityQueueTechnique(arr,K));
    }
}
