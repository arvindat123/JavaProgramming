package org.Leetcode;

public class LengthOfLastWord {

    public static int lengthOfLastWord(String string){
        String[] array = string.split(" ");
        return array[array.length-1].length();
        /**
         * s = s.trim();
         *
         *         int length = 0;
         *         for (int i = s.length() - 1; i >= 0; i--) {
         *             if (s.charAt(i) == ' ') {
         *                 length = s.length() - 1 - i;
         *                 break;
         *             }
         *             if (length == 0) {
         *                 length = s.length();
         *             }
         *         }
         *
         *         return length;
         */
    }

    public static void main(String[] args) {
        int length = LengthOfLastWord.lengthOfLastWord("luffy is still joyboy");
        System.out.println(length);
    }
}
