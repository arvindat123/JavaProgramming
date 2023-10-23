package org.ParameterizeBehaviour;

import java.util.Arrays;
import java.util.List;

public class InsertionSort {

	public static void main(String[] args) {
		int arr[] = {4,2,6,5,1,8};
		int sortedArr[] = {0};
		for(int i = 0; i<arr.length; i++) {
			for(int j = i;j > i; j--) {
				if(arr[j] <= arr[i]) {
					sortedArr[i] = arr[j];
				}
			}
		}
	}

}

