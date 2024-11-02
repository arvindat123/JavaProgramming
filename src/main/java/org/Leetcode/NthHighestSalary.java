package org.Leetcode;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;

public class NthHighestSalary {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();

        map.put("arvind", 100);
        map.put("prabha", 50);
        map.put("aarabh", 150);
        map.put("michael", 200);
        map.put("mark", 151);
        map.put("rodney", 123);
        map.put("rae", 134);
        map.put("anna", 153);
        map.put(null, null);
        map.put(null, null);
        map.put(null, 123);

        map.entrySet().stream().collect(Collectors.toList()).forEach(System.out::println);

       // Map.Entry<String,Integer> m = map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
              //  .collect(Collectors.toList()).get(1);
        //2nd approach
     /*  Map.Entry<String,Integer> m = map.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toList()).get(0);*/
      //System.out.println(m.getKey()+" "+m.getValue());

    }
}
