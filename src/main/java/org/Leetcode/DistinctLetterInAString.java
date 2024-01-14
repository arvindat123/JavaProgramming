package org.Leetcode;

import org.Interface.A;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DistinctLetterInAString {
    public static void main(String[] args) {
        String s = "aabbcdef";
        String sb = s.chars().distinct().mapToObj(c -> String.valueOf((char) c)).collect(Collectors.joining());
        System.out.println((char)43);
        //s.chars().distinct().forEach(c->sb.append((char)c));
    }
}
