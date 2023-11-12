package org.General;

interface Bird{
    void fly();
}
public class InterfaceWithClass implements Bird {
    public void fly(){
        System.out.println("Bird is flying");
    }

    public void classMethod(){
        System.out.println("Class method");
    }

    public static void main(String[] args) {
        Bird obj = new InterfaceWithClass();
        obj.fly();
        ((InterfaceWithClass) obj).classMethod();
    }
}
