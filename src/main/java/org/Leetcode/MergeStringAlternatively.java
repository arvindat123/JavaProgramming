package org.Leetcode;

public class MergeStringAlternatively {
	
	public String mergeAlternately(String word1, String word2) {
        StringBuilder finalWord = new StringBuilder();
        int last = 0;
        int wordLength1 = word1.length();
        int wordLength2 = word2.length();
        String largeWord ="";
        if(wordLength1 > wordLength2) 
            largeWord = word1;
        else 
            largeWord = word2;
        int smallWordLength = Math.min(wordLength1,wordLength2);
        for(int i = 0; i < smallWordLength; i++){
            finalWord.append(word1.charAt(i));
            finalWord.append(word2.charAt(i));
            last = i;
        }
        if(wordLength1 != wordLength2)
        	finalWord.append(largeWord.substring(last+1,largeWord.length()));
        return finalWord.toString();
    }

	public static void main(String[] args) {
		MergeStringAlternatively obj = new MergeStringAlternatively();
		System.out.println(obj.mergeAlternately("arv", "ind"));

			
	}
	
	/*
	public String mergeAlternately(String word1, String word2) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < word1.length() || i < word2.length()) {
            if (i < word1.length()) {
                result.append(word1.charAt(i));
            }
            if (i < word2.length()) {
                result.append(word2.charAt(i));
            }
            i++;
        }
        return result.toString();
    }
	*/

}
