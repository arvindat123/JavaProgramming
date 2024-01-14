package org.Java8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormat {
    public static void main(String[] args) {
        String armisticeDate = "2016-12-31";

        LocalDate aLD = LocalDate.parse(armisticeDate);
        System.out.println("Date: " + aLD);

        String armisticeDateTime = "2016-12-31T11:50";

        LocalDateTime aLDT = LocalDateTime.parse(armisticeDateTime);
        System.out.println("Date/Time: " + aLDT);

        //Convert string to date in custom formats
        //If you have dates in some custom format, then you need to put additional logic to handle formatting as well using DateTimeFormatter.ofPattern().
        String anotherDate = "04 Apr 2016";

        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd MMM yyyy");
        LocalDate random = LocalDate.parse(anotherDate, df);

        System.out.println(anotherDate + " parses as " + random);

    }
}
