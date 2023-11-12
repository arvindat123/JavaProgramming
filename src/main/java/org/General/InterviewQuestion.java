package org.General;

public class InterviewQuestion {
    public static void main(String[] args) {
        System.out.println('b'+'i'+'k'); // 310
        System.out.println('b'); // b
        System.out.println('a'-'A'); //32
        System.out.println('b'-'B'); // 32
        int i = 'a' - 'A';
        System.out.println(i); //32
        System.out.println(0-'a'); //-97
        System.out.println(0-'b'); //-98
        char c = '1';
        int asciiValue = (int) c;
        System.out.println(asciiValue); // 49
        StringBuilder str = new StringBuilder("arvind");
        StringBuilder str1 = new StringBuilder();
            str1.append(str.substring(1,2));
        System.out.println(str1); //r
        System.out.println(Character.isLetterOrDigit(4));


    }
}
