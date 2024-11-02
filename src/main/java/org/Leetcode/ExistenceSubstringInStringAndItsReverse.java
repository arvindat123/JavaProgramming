package org.Leetcode;

import java.util.HashSet;

public class ExistenceSubstringInStringAndItsReverse {
    public static boolean existenceSubstringInStringAndItsReverse(String string){
        HashSet hs = new HashSet<>();
        for(int i =0;i<string.length()-1;i++){
            if(string.charAt(i)==string.charAt(i+1)){
                return true;
            }
            String ss= string.charAt(i+1)+""+string.charAt(i);
            if(hs.contains(ss)){
                return true;
            }
            hs.add(string.charAt(i)+""+string.charAt(i+1));
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(existenceSubstringInStringAndItsReverse("lk"));
        //How to reverse a string
        StringBuilder sb = new StringBuilder("Arvind");
        System.out.println(sb.reverse());
    }
}

/**
 *
3083. Existence of a Substring in a String and Its Reverse
 Given a string s, find any  substring of length 2 which is also present in the reverse of s.

 Return true if such a substring exists, and false otherwise.

 Example 1:

 Input: s = "leetcode"

 Output: true

 Explanation: Substring "ee" is of length 2 which is also present in reverse(s) == "edocteel".

 Example 2:

 Input: s = "abcba"

 Output: true

 Explanation: All of the substrings of length 2 "ab", "bc", "cb", "ba" are also present in reverse(s) == "abcba".

 Example 3:

 Input: s = "abcd"

 Output: false

 Explanation: There is no substring of length 2 in s, which is also present in the reverse of s.
 */
