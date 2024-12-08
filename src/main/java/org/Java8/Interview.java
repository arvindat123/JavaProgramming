package org.Java8;

import java.util.*;

public class Interview {

    public static void main(String[] args) {
        List<Map<String, Object>> list = new ArrayList<>();

        for(int i = 0; i < 20; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("quantity", Math.random());
            map.put("price", Math.random());
            list.add(map);

        }
        list.sort(Comparator.comparing(m -> (Double)m.get("price")));
        System.out.println(list);
        Map<String, Integer> sM = new HashMap<>();
        sM.put("Math", 50);
        sM.put("Socio", 100);
        Map<String, List<SubjectMarks>> listOfHasMap = new HashMap<>();
        listOfHasMap.put("Arvind", Arrays.asList(new SubjectMarks("math", 50), new SubjectMarks("Soci", 50)));
        listOfHasMap.put("Prabha", Arrays.asList(new SubjectMarks("math", 40), new SubjectMarks("Soci", 10)));
        listOfHasMap.put("Aarabh", Arrays.asList(new SubjectMarks("math", 30), new SubjectMarks("Soci", 25)));
        listOfHasMap.put("Vinod", Arrays.asList(new SubjectMarks("math", 70), new SubjectMarks("Soci", 66)));
        listOfHasMap.put("Addhyan", Arrays.asList(new SubjectMarks("math", 80), new SubjectMarks("Soci", 54)));

    /*    listOfHasMap.entrySet().stream()
                .sorted(Comparator.comparing())
                .collect(Collectors
                        .toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));*/
    }
}


class SubjectMarks implements Comparable<SubjectMarks>{
    public String subject;
    public int marks;
    public SubjectMarks(String subject, int marks){
        this.subject = subject;
        this.marks = marks;

    }

    @Override
    public int compareTo(SubjectMarks sub) {
        return (int)(this.getMarks() - sub.getMarks());
    }

    public String getSubject() {
        return subject;
    }

    public int getMarks() {
        return marks;
    }
}

/*class StudentTest {
    String name;
    StudentTest(String name) {
        this.name = name;
    }
}*/

