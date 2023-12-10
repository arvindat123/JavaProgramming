package org.Leetcode;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstring {

	private static int longestSubstring(String string){
		int n = string.length();
		int maxLength = 0;
		Set<Character> charSet = new HashSet<>();
		int left = 0;

		for(int right = 0; right < n ; right++){
			if(!charSet.contains(string.charAt(right))){
				charSet.add(string.charAt(right));
				maxLength = Math.max(maxLength,right - left + 1);
			} else {
				while (charSet.contains(string.charAt(right))){
					charSet.remove(string.charAt(left));
					left++;
				}
				charSet.add(string.charAt(right));
			}
		}
		return maxLength;
	}

	public static void main(String[] args) {
		System.out.println(longestSubstring("abcabcbb"));
	}


	/*
 https://leetcode.com/problems/longest-substring-without-repeating-characters/solutions/3157485/used-hashset-in-java-explained-approach/
	 */ 
}
