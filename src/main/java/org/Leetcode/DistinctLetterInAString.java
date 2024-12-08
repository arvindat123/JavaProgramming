package org.Leetcode;

import java.util.stream.Collectors;

public class DistinctLetterInAString {
    public static void main(String[] args) {
        String s = "aabbcdef";
        String sb = s.chars().distinct().mapToObj(c -> String.valueOf((char) c)).collect(Collectors.joining());
        System.out.println((char)43);
        //s.chars().distinct().forEach(c->sb.append((char)c));
    }
}
