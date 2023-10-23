package org.Leetcode;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
	int a[] = {0,0};
	public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numToIndex = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (numToIndex.containsKey(target - nums[i])) {
            	a[0] = numToIndex.get(target - nums[i]);
            	a[1] = i;
            	break;
            }
            numToIndex.put(nums[i], i);
        }
        return a;
    }
public static void main(String[] args) {
	int a[] = {2,7,11,15};
	TwoSum obj = new TwoSum();
	int b[] = obj.twoSum(a,26);
	for(int i : b) {
		System.out.println(i);
	}
}	
}
