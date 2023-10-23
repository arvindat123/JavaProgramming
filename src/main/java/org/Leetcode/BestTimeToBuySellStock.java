package org.Leetcode;

import java.util.Arrays;

public class BestTimeToBuySellStock {
	

	public int maxProfit(int[] prices) {
		int maxProfitValue = 0;
		for(int i =0; i< prices.length-1; i++) {
			for(int j = i+1; j < prices.length;j++) {
				if(prices[i] < prices[j] && maxProfitValue < (prices[j] - prices[i])) {
					maxProfitValue = prices[j] - prices[i];
				}
			}
		}
		return maxProfitValue;
    }

	public static void main(String[] args) {
		BestTimeToBuySellStock obj = new BestTimeToBuySellStock();
		int price[] = {1,2,3};
		System.out.println(obj.maxProfit(price));
		int arr[] = {8,5,6,4,7,2,8};
		Arrays.sort(arr);
		/*for(int i : arr) {
			System.out.println(arr[i]); // java.lang.ArrayIndexOutOfBoundsException 
		}*/
		for(int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
		
	}
	
	
	//optimised
	
//	{
//        int lsf = Integer.MAX_VALUE; // least so far
//        int op = 0; // overall profit
//        int pist = 0; // profit if sold today
//        
//        for(int i = 0; i < prices.length; i++){
//            if(prices[i] < lsf){ // if we found new buy value which is more smaller then previous one
//                lsf = prices[i]; // update our least so far
//            }
//            pist = prices[i] - lsf; // calculating profit if sold today by, Buy - sell
//            if(op < pist){ // if pist is more then our previous overall profit
//                op = pist; // update overall profit
//            }
//        }
//        return op; // return op 
}
