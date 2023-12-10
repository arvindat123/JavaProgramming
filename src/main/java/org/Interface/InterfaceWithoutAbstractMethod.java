package org.Interface;


public interface InterfaceWithoutAbstractMethod {

   // public abstract void decision();

    default void print(){
        System.out.println("This is printer");
    }

    public static void scan(){
        System.out.println("This is scanner");
    }
}
