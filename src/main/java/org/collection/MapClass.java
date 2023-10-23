package org.collection;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class MapClass {
	public static void main(String... args) {
		
//		There are four ways to traverse Map collections
//		1) for with keySet();
//		2) for with entrySet();
//		3) iterate with keySet();
//		4) iterate with entrySet();
		
		Map<String, String> map = new HashMap<>();
		map.put("Robert C. Martin", "Clean Code");
		map.put("Joshua Bloch", "Effective Java");


		for (String key : map.keySet()) {
			System.out.println("Key : " + key + "--Value : " + map.get(key));
		}

		for (Map.Entry<String, String> mapped : map.entrySet()) {
			System.out.println("Key : " + mapped.getKey() + "=========value : " + mapped.getValue());
		}
		
		Set<String> keyList = map.keySet();
		Iterator itr = keyList.iterator();
		while(itr.hasNext()) {
			String str = (String) itr.next();
			System.out.println("Iterator KeySet() : "+ str+" "+map.get(str));
		}
		
		Set<Map.Entry<String, String>> entrySet = map.entrySet();
		Iterator itr2 = entrySet.iterator();
		while(itr2.hasNext()) {
			Map.Entry<String, String> singleEntry = (Map.Entry<String, String>)itr2.next();
			System.out.println("Iterator map EntrySet ="+ singleEntry.getKey()+" -- "+singleEntry.getValue());
		}
		
		Map<Integer, Integer> map1 = new HashMap<>();
		map1.put(1, 1);
		 ConcurrentHashMap<Integer, String> m
         = new ConcurrentHashMap<>();

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
		 Map<String, String> hMap 
         = new HashMap<String, String>(); 
     
     // add elements into the Map 
     hMap.put("1", "Welcome"); 
     hMap.put("2", "To"); 
     hMap.put("3", "Geeks"); 
     hMap.put("4", "For"); 
     hMap.put(null, null); 
     
     System.out.println("Map : " + hMap); 
     
     // Synchronizing the map 
     Map<String, String> sMap 
         = Collections.synchronizedMap(hMap); 

     // printing the Collection 
     System.out.println("Synchronized map is : "
                        + sMap);
	}

}
