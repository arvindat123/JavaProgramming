package org.General;

class Person2 implements Cloneable {
    private String name;
    private Address2 address;

    public Person2(String name, Address2 address) {
        this.name = name;
        this.address = address;
    }

    // Getter and setter methods

    public String getName() {
        return name;
    }

    public Address2 getAddress() {
        return address;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        // Perform deep cloning
        Person2 clonedPerson = (Person2) super.clone();
        clonedPerson.address = (Address2) address.clone();
        return clonedPerson;
    }
}

class Address2 implements Cloneable {
    private String city;

    public Address2(String city) {
        this.city = city;
    }

    // Getter and setter methods

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

public class DeepCloneExample {
    public static void main(String[] args) throws CloneNotSupportedException {
        Address2 address = new Address2("New York");
        Person2 originalPerson = new Person2("John", address);

        // Deep clone
        Person2 clonedPerson = (Person2) originalPerson.clone();

        // Output: John, New York
        System.out.println(clonedPerson.getName() + ", " + clonedPerson.getAddress().getCity());

        // Modify the original object
        originalPerson.getAddress().setCity("San Francisco");

        // Output: John, New York (changes not reflected in the cloned object)
        System.out.println(clonedPerson.getName() + ", " + clonedPerson.getAddress().getCity());
    }
}
