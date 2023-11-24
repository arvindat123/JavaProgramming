package org.General;

public class SystemOutPrintln {
    static int b;
    static int g=0;
    // final int b; // comiler error Variable 'b' might not have been initialized
    //final static int d;Variable 'd' might not have been initialized
    final int t=10;
    void print(){
        final int e; //work
    }

    final int print(int i){ return -1;}
    final int print(int i, int j){ return -1;} //final method can be overloaded but cannot be overriden

    public static void main(String[] args) {
        System.out.println();
       // System.out.println(null);//Compiler error for ambiguous call to char[] or String[]
        System.out.println(""+null); // null
       // System.out.println(c);
        System.out.println('c'); // c
        System.out.println('c'+'a'); // 196
        System.out.println(10+'c'); // 109
        System.out.println(1+2); // 3
        System.out.println(1+"c"); //1c
        System.out.println(10+20+"c"); //30c
        System.out.println("c"+10+20); //c1020
        System.out.println(0001+'a'); // 98
        System.out.println(0001); // 1
        final int a; // compiled because it is in a method
        final int p=0;
        //static int f = 10; // Modifier 'static' not allowed here
       // static int d;
       // static final int c; //compiler error static not allowed here


    }

}
