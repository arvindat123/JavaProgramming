package org.collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MapClass {
    public static void main(String... args) {

//		There are four ways to traverse Map collections
//		1) for with keySet();
//		2) for with entrySet();
//		3) iterate with keySet();
//		4) iterate with entrySet();
//		5) iterate using Stream

        Map<String, Integer> map = new HashMap<>();
        map.put("arvind", 100);
        map.put("prabha", 50);
        map.put("aarabh", 150);
        map.put("michael", 200);
        map.put("mark", 151);
        map.put("rodney", 123);
        map.put("rae", 134);
        map.put("anna", 153);

        //Iterate map using Lambdas
        map.forEach((key, value) -> System.out.println(key + " , " + value));

        //Get the keys and print it
        for (String key : map.keySet()) {
            System.out.println("Key : " + key + "--Value : " + map.get(key));
        }

        //Get key and values
        for (Map.Entry<String, Integer> mapped : map.entrySet()) {
            System.out.println("Key : " + mapped.getKey() + "=========value : " + mapped.getValue());
        }

        Set<String> keyList = map.keySet();
        System.out.println("printing set=================" + keyList);
        StringJoiner s = new StringJoiner(",");
        s.add("arvind").add("gudiya");
        System.out.println(s);
        Iterator itr = keyList.iterator();
        while (itr.hasNext()) {
            String str = (String) itr.next();
            System.out.println("Iterator KeySet() : " + str + " " + map.get(str));
        }

        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        Iterator itr2 = entrySet.iterator();
        while (itr2.hasNext()) {
            Map.Entry<String, Integer> singleEntry = (Map.Entry<String, Integer>) itr2.next();
            System.out.println("Iterator map EntrySet =" + singleEntry.getKey() + " -- " + singleEntry.getValue());
        }

        Map<Integer, Integer> map1 = new HashMap<>();
        map1.put(1, 1);
        ConcurrentHashMap<Integer, String> m = new ConcurrentHashMap<>();

        // Insert mappings using
        // put method
//     m.put(100, "Hello");
//     m.put(101, "Geeks");
//     m.put(102, "Geeks");
//    // m.put(null, null); //not allowed
//     
//     String s1 = "java";
//     String s2 = new String("java");
//     String s3 = "java";
//     System.out.println(s1.equals(s2));
        Map<String, String> hMap = new HashMap<String, String>();
        Map<Integer, String> synchronizedMap = Collections.synchronizedMap(new HashMap<>());
        ConcurrentHashMap<Integer, String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put(1, "Value1");
        concurrentHashMap.put(2, "Value2");

        concurrentHashMap.forEach((key, value) -> System.out.println(key+" "+ value));

        // add elements into the Map
        hMap.put("1", "Welcome");
        hMap.put("2", "To");
        hMap.put("3", "Geeks");
        hMap.put("4", "For");
        hMap.put(null, null);

        hMap.forEach((key,value) -> System.out.println(key+value));

        System.out.println("Map : " + hMap);

        // Synchronizing the map
        Map<String, String> sMap = Collections.synchronizedMap(hMap);

        // printing the Collection
        System.out.println("Synchronized map is : " + sMap);
    }

}
