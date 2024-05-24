package org.example;

import java.util.*;

public class Graph {
    Map<Object, Knoten> knoten = new HashMap<>();
    public int size(){
        return knoten.size();
    }
    public Collection<Knoten> getAllKnoten(){
        return knoten.values();
    }
    public Knoten getKnoten(Object object) {
        return knoten.get(object);
    }

    public Graph(Set<List<Object>> graph) {
        graph.forEach(kante -> kante.forEach(object -> {
            if (!knoten.containsKey(object)) {
                knoten.put(object, new Knoten(object, knoten.size()));
            }
        }));

        graph.forEach(kante ->{
            for (int i = 0; i < kante.size(); i++) {
                Knoten k = knoten.get(kante.get(i));
                for (int j = 0; j < kante.size(); j++) {
                    if (j != i){
                        k.addNachbarn(knoten.get(kante.get(j)));
                    }
                }
            }
        });
    }
}
