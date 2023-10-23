package org.Leetcode;

import java.util.Arrays;

public class LongestCommonPrefixProblem {

	//https://leetcode.com/problems/longest-common-prefix/solutions/3174307/well-explained-code-using-strings-in-java/
	  public String longestCommonPrefix(String[] strs) {
		  Arrays.sort(strs);
		  String prefix = "";
		  int lengthOfSmallestString = 0;
		  for(int i = 0; i < strs.length; i++) {
			  int newLength = strs[i].length();
			  lengthOfSmallestString = Math.max(lengthOfSmallestString, newLength);
		  }
		  for(int j = 0; j < lengthOfSmallestString; j++) {
			  char c = 0;
			  for(int k = 0; k < strs.length; k++) {
				  
				  c = strs[k].charAt(j);
				  if(k != 0 && prefix.equalsIgnoreCase(c+"")) {
					  
				  }
				  
			  }
			  prefix.concat(c+"");
		  }
	  
		  return prefix;
	    }
	  public static void main(String[] args) {
		  LongestCommonPrefixProblem common = new LongestCommonPrefixProblem();
		  String[] strs = {"flower","flow"};
		  common.longestCommonPrefix(strs);
		System.out.println(common.longestCommonPrefix(strs));
	}
	  
}
