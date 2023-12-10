package org.inheritance;

public class ObjectAndStringEquality
{
    public static void main(String[] args) {
        ObjectAndStringEquality obj1 = new ObjectAndStringEquality();
        ObjectAndStringEquality obj2 = new ObjectAndStringEquality();
        System.out.println(obj1 + " ---"+obj2);
        if(!obj1.equals(obj2)) {
            System.out.println("Objects are not equals");
        }
        if(!(obj1 == obj2)) {
            System.out.println("Memory address also not same");
        }
        if(obj1 instanceof Object){
            System.out.println("obj1 is an Object");
        }

        String s1 = "arvind";
        String s2 = "arvind";
        System.out.println(s1.equals(s2)); //true
        System.out.println(s1 == s2); // true
        String s3 = new String("arvind"); // new object is created
        String s4 = new String("arvind"); // new object is created
        System.out.println(s3.equals(s4)); // true as it compares string
        System.out.println(s3 == s4); // false because it compares memory location

        s1 = "arvind";
        s2 = "arvin" + "d";
        System.out.println(s1.equals(s2)); //true
        System.out.println(s1 == s2); // true
    }
}
