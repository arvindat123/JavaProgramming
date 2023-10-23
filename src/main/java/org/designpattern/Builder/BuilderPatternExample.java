package org.designpattern.Builder;

// Product class - the complex object to be created
/*
The Product class represents the complex object to be constructed.
The Builder interface declares methods for building different parts of the product.
The ConcreteBuilder class implements the Builder interface and provides the concrete implementation for building the product.
The Director class orchestrates the construction process using a specific builder.
The client code demonstrates how to use the Builder pattern to construct a Product with a specific configuration.

*/
class Product {
    private String part1;
    private String part2;
    private String part3;

    public void setPart1(String part1) {
        this.part1 = part1;
    }

    public void setPart2(String part2) {
        this.part2 = part2;
    }

    public void setPart3(String part3) {
        this.part3 = part3;
    }

    @Override
    public String toString() {
        return "Product [part1=" + part1 + ", part2=" + part2 + ", part3=" + part3 + "]";
    }
}

// Builder interface
interface Builder {
    void buildPart1(String part1);
    void buildPart2(String part2);
    void buildPart3(String part3);
    Product getResult();
}

// ConcreteBuilder class implementing the Builder interface
class ConcreteBuilder implements Builder {
    private Product product = new Product();

    @Override
    public void buildPart1(String part1) {
        product.setPart1(part1);
    }

    @Override
    public void buildPart2(String part2) {
        product.setPart2(part2);
    }

    @Override
    public void buildPart3(String part3) {
        product.setPart3(part3);
    }

    @Override
    public Product getResult() {
        return product;
    }
}

// Director class that orchestrates the construction using a builder
class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public Product construct() {
        builder.buildPart1("Part1");
        builder.buildPart2("Part2");
        builder.buildPart3("Part3");
        return builder.getResult();
    }
}

// Client code
public class BuilderPatternExample {
    public static void main(String[] args) {
        // Creating a ConcreteBuilder
        Builder builder = new ConcreteBuilder();

        // Creating a Director and configuring it with the ConcreteBuilder
        Director director = new Director(builder);

        // Constructing the product
        Product product = director.construct();

        // Displaying the product
        System.out.println(product);
    }
}


