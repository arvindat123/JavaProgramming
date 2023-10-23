package org.Learning;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SetExample {
	
	public static int longestConsecutive(int[] nums) {
	       int longestSeq = 0;
			Set<Integer> hash_set = new HashSet<Integer>();
	       for(int num : nums)
	       {
	    	   hash_set.add(num);
	       }
	       for(int i : nums)
	       {
	    	   if(!hash_set.contains(i-1))
	    	   {
	    		   longestSeq =+1;
	    	   }
	       }
	       
	       
	       return 0;
			  
	    }
	public static void main(String[] args) {
		longestConsecutive(new int[] {2,5,1,3,2});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*Set<String> hash_Set = new HashSet<>();
		
		 	hash_Set.add("Geeks");
	        hash_Set.add("For");
	        hash_Set.add("Geeks");//removed because of duplicate element
	        hash_Set.add("Example");
	        hash_Set.add("Set");
	        hash_Set.add(null);
	        hash_Set.add(null);
	        
	        System.out.println(hash_Set);
	        Object[] obj = hash_Set.toArray();
	       for(int i=0;i<obj.length;i++)
	       {
	    	   System.out.println(obj[i]);
	       }*/
	   	}
}
