package org.General;

public class DivideByZero {

    public static void main(String[] args) {
        double a, b, c, d, e, f, g;
        a = 3.0 / 0; //Infinity

        b = 0 / 4.0; // 0.0
        g = 0 / 5;//0.0

        c = 0 / 0.0;//NaN
        e = 0.0 / 0;//NaN

        //f=5/0;//ArithmeticException: / by zero
        //d=0/0; //divide by zero Arithmetic Exception

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(e);
        System.out.println(g);
        //System.out.println(f);
    }
}


