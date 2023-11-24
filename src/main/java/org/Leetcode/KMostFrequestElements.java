package org.Leetcode;
/*Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
    Example 1:

    Input: nums = [1,1,1,2,2,3], k = 2
    Output: [1,2]
    Example 2:

    Input: nums = [1], k = 1
    Output: [1]

 */

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class KMostFrequestElements {
    public static void main(String[] args) {
        int[] array = new int[]{1,1,1,2,2,2,4,4,4,3};
        int kMostFrequentElement = 2;

        Map<Integer,Integer> map = new HashMap<>();
        for(int num : array){
            map.put(num,map.getOrDefault(num,0)+1);
        }

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((a,b) -> map.get(b) - map.get(a));

        priorityQueue.addAll(map.keySet());

        for(int i=0;i<kMostFrequentElement;i++){
            System.out.println(priorityQueue.poll());
        }
    }
}
