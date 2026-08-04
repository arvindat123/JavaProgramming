package org.Leetcode;

public class ValidPalindrome {
    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        System.out.println(new ValidPalindrome().isPalindrome(s));
    }

    public boolean isPalindrome(String s) {
        if (s.isEmpty()) return true;
        StringBuilder string = new StringBuilder(s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase());

        if (string.reverse().toString().contentEquals(string)) {
            System.out.println("String is palindrome");
            return true;
        } else {
            System.out.println("String is not a palindrome");
            return false;
        }

    }
}
