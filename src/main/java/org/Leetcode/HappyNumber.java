package org.Leetcode;

public class HappyNumber {
    public boolean isHappyNumber(int number){
        if(number % 10 == 0) return false;
        int n =0;
        while(true){
            int n1 = number % 10;
            n = n1*n1;
            int n2 = number / 10;
            n += (n2*n2);
            if(n == 1) return true;
            else isHappyNumber(n);
        }
    }

    public static void main(String[] args) {
        System.out.println(new HappyNumber().isHappyNumber(19));
    }
}
