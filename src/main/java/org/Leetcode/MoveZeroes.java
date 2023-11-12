package org.Leetcode;

import java.util.Arrays;

public class MoveZeroes {
    public static void main(String[] args) {
       int nums[] = {1,0,1,0,1,0,1,0,0,0,1,1,1};
       int numIndex=0;
       for(int i=0; i< nums.length;i++){
           if(nums[i] !=0 ){
               nums[numIndex++] = nums[i] ;
           }
       }
       while(numIndex < nums.length){
           nums[numIndex++] = 0;
       }
       for(int t : nums){
           System.out.println(t);
       }
    }
}


 /*   Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the
 non-zero elements.

        Note that you must do this in-place without making a copy of the array.

        Example 1:

        Input: nums = [0,1,0,3,12]
        Output: [1,3,12,0,0]
        Example 2:

        Input: nums = [0]
        Output: [0]

solution:

        int nums[] = {1};
        int i=0,j=0;
        while(j < nums.length) {
        if(nums[i] != nums[j] && nums[i] == 0){
        nums[i] = nums[j];
        nums[j] = 0;
        i++; j++;
        } else if (nums[i] == 0 && nums[j]==0) {
        j++;
        }
        }
        for(int p : nums){
        System.out.println(p);
        }

        */
