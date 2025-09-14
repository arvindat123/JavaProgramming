package org.Test;

import org.Interface.C;

public class BuilderPatternExample {
    public static void main(String[] args) {
        Computer computer = new Computer.ComputerBuilder("16GB", "512GB SSD").setCpu("22").build();
        System.out.println(computer);
    }
}

class Computer {
    private String ram;
    private String storage;
    private String cpu;
    private String gpu;
    private String operatingSystem;
    private Computer(ComputerBuilder builder) {
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.cpu = builder.cpu;
        this.gpu = builder.gpu;
        this.operatingSystem = builder.operatingSystem;
    }

    // Getters for all attributes
    public String getRam() {
        return ram;
    }

    public String getStorage() {
        return storage;
    }

    public String getCpu() {
        return cpu;
    }

    public String getGpu() {
        return gpu;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "ram='" + ram + '\'' +
                ", storage='" + storage + '\'' +
                ", cpu='" + cpu + '\'' +
                ", gpu='" + gpu + '\'' +
                ", operatingSystem='" + operatingSystem + '\'' +
                '}';
    }

    public static class ComputerBuilder {
        private String ram;
        private String storage;
        private String cpu;
        private String gpu;
        private String operatingSystem;

        public ComputerBuilder(String ram, String Storage){
            this.ram = ram;
            this.storage = Storage;
        }

        public ComputerBuilder setCpu(String cpu){
            this.cpu = cpu;
            return this;
        }

        public ComputerBuilder setGpu(String gpu) {
            this.gpu = gpu;
            return this;
        }

        public ComputerBuilder setOperatingSystem(String operatingSystem) {
            this.operatingSystem = operatingSystem;
            return this;
        }

        public Computer build(){
            return new Computer(this);
        }
    }
}
