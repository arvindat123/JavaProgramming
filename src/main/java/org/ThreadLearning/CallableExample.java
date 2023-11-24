package org.ThreadLearning;// Java Program to illustrate Callable interface
 
// Importing classes from java.util package 
import java.util.Random;  
import java.util.concurrent.Callable;  
import java.util.concurrent.Future;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
  
// Class
// Implementing the Callable interface
class CallableMessage implements Callable<String>{
  public String call() throws Exception{
      return "Hello World!";
  }  
}
 
public class CallableExample{
  static ExecutorService executor = Executors.newFixedThreadPool(2);
    public static void main(String[] args) throws Exception{
        CallableMessage task = new CallableMessage();
         Future<String> message = executor.submit(task);
         System.out.println(message.get().toString());
    }
}