package org.Learning;

public class FinalizeMethod {
	
	    public static void main(String[] args)
	    {
	    	FinalizeMethod j = new FinalizeMethod();
	 
	        // Calling finalize method Explicitly.
	        //j.finalize();
	 
	        j = null;
	 
	        // Requesting JVM to call Garbage Collector method
	        System.gc();
	        System.out.println("Main Completes");
	    }
	 
	    // Here overriding finalize method
	    public void finalize()
	    {
	        System.out.println("finalize method overridden");
	        System.out.println(10 / 0);
	    }
	
}
