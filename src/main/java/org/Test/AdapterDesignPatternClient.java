package org.Test;



public class AdapterDesignPatternClient {

    public static void main(String[] args) {
        LegacyPrinter legacyPrinter = new LegacyPrinter();

        ModernPrinter printer = new PrinterAdapter(legacyPrinter);
        printer.print("Hello world");
    }
}

class PrinterAdapter implements  ModernPrinter {

    private LegacyPrinter legacyPrinter;

    public PrinterAdapter(LegacyPrinter legacyPrinter){
        this.legacyPrinter = legacyPrinter;
    }

    @Override
    public void print(String content){
        legacyPrinter.printDocument(content);
    }

}

interface ModernPrinter {
    void print(String content);
}

class LegacyPrinter {
    public void printDocument(String text){
        System.out.println("Legacy Printer : "+ text);
    }
}