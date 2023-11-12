package org.General;

class Person implements Cloneable {
    private String name;
    private Address address;

    public Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    // Getter and setter methods

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class Address {
    private String city;

    public Address(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}

public class ShallowCloneExample {
    public static void main(String[] args) throws CloneNotSupportedException {
        Address address = new Address("New York");
        Person originalPerson = new Person("John", address);

        // Shallow clone
        Person clonedPerson = (Person) originalPerson.clone();

        // Output: John, New York
        System.out.println(clonedPerson.getName() + ", " + clonedPerson.getAddress().getCity());

        // Modify the original object
        originalPerson.getAddress().setCity("San Francisco");

        // Output: John, San Francisco (changes reflected in the cloned object)
        System.out.println(clonedPerson.getName() + ", " + clonedPerson.getAddress().getCity());
    }
}
