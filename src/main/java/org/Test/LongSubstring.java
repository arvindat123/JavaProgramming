package org.Test;

import java.util.ArrayList;
import java.util.List;

public class LongSubstring {
    public int lengthOfLongestSubstring(String s) {
        char[] character = s.toCharArray();
        int len= 0;
        int maxLen = 0;
        List<Character> list = new ArrayList<>();
        for(char c : character){
            if(!list.contains(c)){
                list.add(c);
                maxLen = Math.max(maxLen, list.size());
            } else{
                list.clear();
                list.add(c);
            }
        }
        return Math.max(maxLen, list.size());
    }

    public static void main(String[] args) {
        System.out.println(new LongSubstring().lengthOfLongestSubstring("abcabcbb"));
    }
}
