package org.General;
//Declaration outside a class
enum Color {
    RED,
    GREEN,
    BLUE;
}

public class EnumExample {
    //Declaration inside a class
    enum DayType {
        MONDAY,
        TUESDAY,
        SUNDAY;
    }
    public static void main(String[] args) {
        Color color = Color.RED;
        DayType day = DayType.TUESDAY;
        System.out.println(color);
        System.out.println(day);
    }
}

