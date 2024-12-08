package org.Java11;

import jakarta.validation.constraints.NotNull;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LocalVariableSyntaxForLambdaParameters {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("a","b","c");
        //String result = list.stream().map(String::toUpperCase).collect(Collectors.joining(","));
        //String result = list.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(","));
        /*This JEP adds support for the var keyword in lambda parameters.
        The benefit is now we can add annotations to the lambda parameters*/
        //String result = list.stream().map((var x) -> x.toUpperCase()).collect(Collectors.joining(","));
        String result = list.stream().map((@NotNull var x) -> x.toUpperCase()).collect(Collectors.joining(","));
        System.out.println(result);//A,B,C

        String codepoint = "U+1F92A";   // crazy face
        System.out.println(convertCodePoints(codepoint));

    }

    // Java, UTF-16
    // Convert code point to unicode
    static char[] convertCodePoints(String codePoint) {
        Integer i = Integer.valueOf(codePoint.substring(2), 16);
        char[] chars = Character.toChars(i);
        return chars;

    }



}
