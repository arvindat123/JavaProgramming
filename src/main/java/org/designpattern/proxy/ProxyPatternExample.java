package org.designpattern.proxy;

// Subject interface
interface RealSubject {
    void request();
}

// RealSubject implementation
class RealSubjectImpl implements RealSubject {
    @Override
    public void request() {
        System.out.println("RealSubject: Handling request.");
    }
}

// Proxy class
class Proxy implements RealSubject {
    private RealSubjectImpl realSubject;

    @Override
    public void request() {
        // Lazy initialization: create the RealSubject only when necessary
        if (realSubject == null) {
            realSubject = new RealSubjectImpl();
        }

        // Additional logic before delegating to the RealSubject
        System.out.println("Proxy: Logging before request.");

        // Delegating the request to the RealSubject
        realSubject.request();

        // Additional logic after delegating to the RealSubject
        System.out.println("Proxy: Logging after request.");
    }
}


public class ProxyPatternExample {
    public static void main(String[] args) {
        // Using the Proxy
        RealSubject proxy = new Proxy();

        // The request is delegated to the RealSubject through the Proxy
        proxy.request();
    }
}
  /*  The Proxy Pattern is a structural design pattern that provides a surrogate
    or placeholder for another object to control access to it. This pattern is
        often used to implement lazy loading, access control, logging, or monitoring.
        I'll provide a simple example of a proxy pattern in Java.

        Let's consider an interface called RealSubject that represents a real object,
        and a Proxy class that acts as a surrogate:
    RealSubject is an interface that declares the request method.
        RealSubjectImpl is the actual implementation of RealSubject.
        Proxy implements RealSubject and controls access to the RealSubject object.
        The Proxy class contains additional logic (in this case, logging) before
        and after delegating the request to the RealSubject.
    This demonstrates how the Proxy controls
    access to the RealSubject by adding extra behavior before and after delegating the request.*/
