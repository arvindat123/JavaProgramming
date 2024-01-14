package org.General;

public class FinalImmutableClass {
    private final String name;
    private final int age;

    // Constructor initializes all fields
    private FinalImmutableClass(String name, int age) {
        this.name = name;
        this.age = age;
    }
    //There must not be any setter/mutator method in the class
    // Accessor methods for all fields (no mutators)
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
    
    public final void run() {
    	
    }
    public final void run(int o) {
    	
    }

    // Example of a method that returns a new instance with modified values
    public FinalImmutableClass withAgeIncremented() {
        return new FinalImmutableClass(this.name, this.age + 1);
    }
}
