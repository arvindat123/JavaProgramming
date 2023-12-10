package org.Leetcode;

public class ReverseNumber {
    public static void main(String[] args) {
        int number = 1000; // Replace this with the number you want to reverse
        int reversedNumber = reverseNumber(number);

        System.out.println("Original number: " + number);
        System.out.println("Reversed number: " + reversedNumber);
    }

    // Function to reverse a number
    public static int reverseNumber(int num) {
        int reversedNum = 0;

        while (num != 0) {
            int digit = num % 10;
            reversedNum = reversedNum * 10 + digit;
            num /= 10;
        }

        return reversedNum;
    }
}
