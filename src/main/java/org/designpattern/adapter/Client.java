package org.designpattern.adapter;

/*Suppose you have an existing LegacyRectangle class that represents rectangles using
the (x, y, width, height) format, but you want to use a modern Rectangle interface
that uses (x1, y1, x2, y2) format.*/
//LegacyRectangle (Existing Class):
 class LegacyRectangle {
    private int x;
    private int y;
    private int width;
    private int height;
    public LegacyRectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
//Rectangle (Target Interface):
 interface Rectangle {
    int getX1();
    int getY1();
    int getX2();
    int getY2();
}

//    Adapter Implementation:
//Now, let's create an adapter class LegacyRectangleAdapter that adapts the LegacyRectangle
// class to the Rectangle interface.
 class LegacyRectangleAdapter implements Rectangle {
    private LegacyRectangle legacyRectangle;

    public LegacyRectangleAdapter(LegacyRectangle legacyRectangle) {
        this.legacyRectangle = legacyRectangle;
    }

    @Override
    public int getX1() {
        return legacyRectangle.getX();
    }

    @Override
    public int getY1() {
        return legacyRectangle.getY();
    }

    @Override
    public int getX2() {
        return legacyRectangle.getX() + legacyRectangle.getWidth();
    }

    @Override
    public int getY2() {
        return legacyRectangle.getY() + legacyRectangle.getHeight();
    }
}

//Client Code:
//Now, you can use the Rectangle interface with objects of the LegacyRectangle class using the adapter.
//In this example, the LegacyRectangleAdapter allows the LegacyRectangle class to be used as if
// it implements the Rectangle interface. The adapter converts the (x, y, width, height) format
// to (x1, y1, x2, y2) format, making it compatible with the desired interface.
public class Client {
    public static void main(String[] args) {
        LegacyRectangle legacyRectangle = new LegacyRectangle(10, 20, 30, 40);

        // Using the adapter to treat LegacyRectangle as a Rectangle
        Rectangle adaptedRectangle = new LegacyRectangleAdapter(legacyRectangle);

        // Now, you can work with the Rectangle interface
        System.out.println("X1: " + adaptedRectangle.getX1());
        System.out.println("Y1: " + adaptedRectangle.getY1());
        System.out.println("X2: " + adaptedRectangle.getX2());
        System.out.println("Y2: " + adaptedRectangle.getY2());
    }
}