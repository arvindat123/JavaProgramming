package org.Interface;

public interface InterfaceWithoutAbstractMethod {

    default void print(){
        System.out.println("This is printer");
    }

    public static void scan(){
        System.out.println("This is scanner");
    }
}
