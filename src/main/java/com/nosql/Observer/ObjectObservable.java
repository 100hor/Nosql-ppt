package com.nosql.Observer;

import java.util.LinkedList;
import java.util.List;

public class ObjectObservable implements Observable{
    private List<Observer> observers;

    public ObjectObservable() {
        observers = new LinkedList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(String log) {
        for (Observer observer : observers)
            observer.update(log);
    }

}
