package org.exceptionhandling;

public class TryCatchFinallyBlock {
	
	public static String exceptionMethod() {
		try {
			String str = null;
			System.out.println(str.length());
			System.out.println("After exception");
			return "from try";
			//System.exit(1);   //System.exit(n) is effectively equivalent tothe call: Runtime.getRuntime().exit(n)


		} catch(Exception e) {
			System.out.println(e.getMessage());
			return "from catch";
		}
		finally {
			System.out.println("Inside finally block");
		}
	}
	
	public static void main(String[] args) {
	System.out.println(TryCatchFinallyBlock.exceptionMethod());
	}
}
