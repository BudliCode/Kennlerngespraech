package org.example;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Knoten {

    private final Object object;
    public Object getObject() {
        return object;
    }

    private final int id;
    public int getId() {
        return id;
    }

    private Set<Knoten> nachbarn = new HashSet<>();
    public Iterable<Knoten> getNachbarn() {
        return nachbarn;
    }
    public boolean addNachbarn(Knoten nachbar) {
        return nachbarn.add(nachbar);
    }

    public Knoten(Object object, int id) {
        this.object = object;
        this.id = id;
    }

    /*
    @Override
    public int hashCode() {
        return object.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Knoten kobj){
            return object.equals(kobj.getObject());
        }
        return false;
    }

     */
}
