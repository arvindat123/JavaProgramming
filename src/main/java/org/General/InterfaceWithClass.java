package org.General;

interface Bird{
    void fly();
}
interface Crow extends Bird {
    //int fly();  //'fly()' in 'org.General.Crow' clashes with 'fly()' in 'org.General.Bird'; attempting to use incompatible return type
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
