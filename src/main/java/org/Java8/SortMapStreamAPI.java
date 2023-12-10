package org.Java8;

import java.util.*;
import java.util.stream.Collectors;

/*Steps to sort a Map in Java 8.
        Convert a Map into a Stream
        Sort it
        Collect and return a new LinkedHashMap (keep the order)*/
        //P.S By default, Collectors.toMap will returns a HashMap
public class SortMapStreamAPI {
    public static void main(String[] args) {
        Map<String, Integer> unsortMap = new HashMap<>();
        unsortMap.put("z", 10);
        unsortMap.put("b", 5);
        unsortMap.put("a", 6);
        unsortMap.put("c", 20);
        unsortMap.put("d", 1);
        unsortMap.put("e", 7);
        unsortMap.put("y", 8);
        unsortMap.put("n", 99);
        unsortMap.put("g", 50);
        unsortMap.put("m", 2);
        unsortMap.put("f", 9);

        System.out.println("Original...");
        System.out.println(unsortMap);  //Order is not as inserted, Insertion order is not maintained
        // sort by keys, a,b,c..., and return a new LinkedHashMap
        // toMap() will returns HashMap by default, we need LinkedHashMap to keep the order.

        Map<String,Integer> sortedMapKey = unsortMap.entrySet().stream().sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(p-> p.getKey(), p-> p.getValue(),(oldVal,newVal) -> oldVal, LinkedHashMap::new));
       // System.out.println(sortedMapKey);

        // Not Recommend, but it works.
        //Alternative way to sort a Map by keys, and put it into the "result" map
        Map<String, Integer> mapppp = new LinkedHashMap<>();
       // unsortMap.entrySet().stream().sorted(Map.Entry.comparingByKey().reversed())
        Map<String, Integer> result2 = new LinkedHashMap<>();
        unsortMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder())) //sort in descending order
                .forEachOrdered(x -> result2.put(x.getKey(), x.getValue()));

        System.out.println("Sorted...by Keys");
        System.out.println(sortedMapKey);
        System.out.println(result2);
        System.out.println("Sorted...by Values");
        Map<String,Integer> result3 = unsortMap.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(p-> p.getKey(),p->p.getValue(),(oldVal,newVal) ->oldVal,LinkedHashMap::new));
        System.out.println(result3);
        //Alternative way
        Map<String, Integer> result4 = new LinkedHashMap<>();
        unsortMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()) // Descreasing order
                .forEachOrdered(x -> result4.put(x.getKey(), x.getValue()));
        System.out.println(result4);

//Sort Map<Object,Object>

        Properties properties = System.getProperties();

        // not easy to sort this
        Set<Map.Entry<Object, Object>> entries = properties.entrySet();
        System.out.println(entries);
        LinkedHashMap<String, String> collect = entries.stream()
                //Map<String, String>
                .collect(Collectors.toMap(k -> (String) k.getKey(), e -> (String) e.getValue()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        collect.forEach((k, v) -> System.out.println(k + ":" + v));
    }
}
