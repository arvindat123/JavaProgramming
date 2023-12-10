package org.Java8;

import java.util.*;

public class MainClass {

    public static boolean isUppercase(String s) {
        return s.chars().allMatch(Character::isUpperCase);
    }

    public static boolean isLowercase(String s) {
        return s.chars().allMatch(Character::isLowerCase);
    }

    public static void main(String[] args) {
        System.out.println(isLowercase("Hello"));
        System.out.println(isUppercase("HELLO"));
        System.out.println(isLowercase("hello"));
        System.out.println(isUppercase("Hello"));
    }


}