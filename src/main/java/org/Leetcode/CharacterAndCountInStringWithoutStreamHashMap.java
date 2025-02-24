package org.Leetcode;

public class CharacterAndCountInStringWithoutStreamHashMap {
    public static void main(String[] args) {
        String paragraph = "I am going to market.";


        //Other approach Stream and HashMap
        //Simple approach
        int[] arrOfAllCharacter = new int[256];
        for (int i = 0; i < paragraph.length(); i++) {
            arrOfAllCharacter[paragraph.charAt(i)]++;
        }
        for (int j = 0; j < 256; j++) {
            if(arrOfAllCharacter[j] != 0){
                System.out.println(arrOfAllCharacter[j]+"="+(char)j);
            }
        }
    }
}
