package org.Java21;

public class PatternMatchingForSwitch {
    static String format(Object obj){
        return switch(obj){
            case String s -> "String length = " + s.length();
            case Double d -> "Double = " + d;
            //guard condition
            case Integer i when i > 0 -> "Positive integer";
            case Integer i when i < 0 -> "Negative integer";
            default -> "Unknown";
        };
    }

    static void main() {
        System.out.println(format("Java"));
        System.out.println(format(12));
        System.out.println(format(12.2));
        System.out.println(format('c'));
    }
    /*
    Eliminates explicit casting
    Cleaner code
    Better readability
    Compile time type checking
     */
}
