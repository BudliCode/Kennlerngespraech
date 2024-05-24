package org.example;

import java.util.*;

public class Distance {
    public static int distance(Object sourceObj, Object destinationObj, Set<List<Object>> graphList) {
        Graph graph = new Graph(graphList);
        int[] distance = new int[graph.size()];
        Arrays.fill(distance, -1);
        boolean[] marked = new boolean[graph.size()];
        Arrays.fill(marked, false);

        Knoten source = graph.getKnoten(sourceObj);
        Knoten destination = graph.getKnoten(destinationObj);

        Queue<Knoten> queue = new LinkedList<>();
        queue.add(source);
        distance[source.getId()] = 0;
        marked[source.getId()] = true;

        while (!queue.isEmpty()){
            Knoten knoten = queue.poll();
            for (Knoten nachbar : knoten.getNachbarn()){
                int nachbarId = nachbar.getId();
                if (!marked[nachbarId]){
                    if (nachbar == destination){
                        return distance[knoten.getId()] + 1;
                    }
                    distance[nachbarId] = distance[knoten.getId()] + 1;
                    marked[nachbarId] = true;
                    queue.add(nachbar);
                }
            }


        }
        return -1;
    }
}
