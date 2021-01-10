package com.nosql.Memento;

import java.util.ArrayList;
import java.util.List;

public class Caretaker {
    private List<Memento> memento=new ArrayList<>();

    public Memento getMemento(int i) {
        return memento.get(i);
    }

    public void addMemento(Memento memento) {
        this.memento.add(memento);
    }
}
