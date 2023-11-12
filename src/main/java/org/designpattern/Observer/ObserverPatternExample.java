package org.designpattern.Observer;
import java.util.ArrayList;
import java.util.List;
// Subject
interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

// ConcreteSubject
class ConcreteSubject implements Subject {
    private int state;
    private List<Observer> observers = new ArrayList<>();

    public void setState(int state) {
        this.state = state;
        notifyObservers();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(state);
        }
    }
}

// Observer
interface Observer {
    void update(int state);
}

// ConcreteObserver
class ConcreteObserver implements Observer {
    private int observerState;

    public void update(int state) {
        observerState = state;
        System.out.println("Observer updated with state: " + observerState);
    }
}

public class ObserverPatternExample {
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();

        ConcreteObserver observer1 = new ConcreteObserver();
        ConcreteObserver observer2 = new ConcreteObserver();

        subject.addObserver(observer1);
        subject.addObserver(observer2);

        subject.setState(10);

        subject.removeObserver(observer1);

        subject.setState(20);
    }
}

/*
Subject: The subject is the object that holds the state and maintains a list of its dependents,known as observers.
The subject provides methods to attach, detach, and notify observers.
When the state of the subject changes, it notifies all registered observers.

Observer: The observer is an interface or an abstract class that defines the update method.
Concrete observer classes implement the update method to respond to changes in the subject's state.

ConcreteSubject: ConcreteSubject is a concrete implementation of the Subject interface. It maintains
the state and triggers notifications to its observers when the state changes.

ConcreteObserver: ConcreteObserver is a concrete implementation of the Observer interface.
It registers with a subject to receive notifications and implements the update method to respond to changes.*/
