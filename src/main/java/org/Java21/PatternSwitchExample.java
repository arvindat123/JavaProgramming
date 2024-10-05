package org.Java21;

public class PatternSwitchExample {
    public static void main(String[] args) {
        Object obj = "Hello";

        String result = switch (obj) {
            case Integer i -> "Integer: " + i;
            case String s -> "String: " + s;
            default -> "Unknown type";
        };
        System.out.println(result);
    }
}