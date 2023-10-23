package org.Leetcode;

public class LongestPalindrome {

	public static String longestPalindrome(String s) {
        String result = "";
        int resultLength = 0;
        int strLength = s.length();
        
        for(int i = 0; i < strLength; i++) {
        	//odd length
        	int right = i, left = i;
        	while(left >= 0 && right < strLength && s.charAt(left) == s.charAt(right)){
        		if((right - left + 1) > resultLength ) {
        			resultLength = right - left +1;
        			result = s.substring(left, right+1);
        		}
        		left--;
        		right++;
        	}
        	
        	//even length
        	right = i+1;
        	left = i;
        	while(left >= 0 && right < strLength && s.charAt(left) == s.charAt(right)){
        		if((right - left + 1) > resultLength ) {
        			resultLength = right - left +1;
        			result = s.substring(left, right+1);
        		}
        		left--;
        		right++;
        	}
        }
        return result;
    }
	
	public static void main(String[] args) {
		System.out.println(longestPalindrome("cbbd"));

	}

}
