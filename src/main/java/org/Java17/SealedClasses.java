package org.Java17;

import java.awt.*;
//https://docs.oracle.com/en/java/javase/17/language/sealed-classes-and-interfaces.html#GUID-0C709461-CC33-419A-82BF-61461336E65F

public sealed class SealedClasses permits Rectangle {
}

final class Rectangle extends SealedClasses{

}
