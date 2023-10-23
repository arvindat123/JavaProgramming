package org.ParameterizeBehaviour;

import java.util.Arrays;

public class SelectionSort {

	public static void main(String[] args) {
		
		int arr[] = {5,2,7,6,9,3};
		int arrLength = arr.length;
		int j = 0;
		int temp1;
		int current = 0;
		for(int p : arr) {
			System.out.println(p);
		}
		for(int i = 0; i < arrLength - 1; i++) {
			current = i;
				for(j = i+1; j < arrLength; j++) {
					if(arr[current] > arr[j]) {
						current = j;
					}
				}
			temp1 = arr[current];
			arr[current] = arr[i];
			arr[i] = temp1;
		}
		System.out.println("sorted elements");
		for(int p : arr) {
			System.out.println(p);
		}
	}

}
