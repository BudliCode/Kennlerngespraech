package org.example;

import java.util.*;

/**
 * implementiert die methode distance()
 */
public class Distance {

    /**
     * konvertiert den Graphen von einem Set aus Edges zu einer Map aus Knoten und dessen Nachbarn
     * @param graph Set aus Node Tupeln, zwischen denen Edges verlaufen.
     * @return Map aus Nodes, die auf ihre jeweiligen Nachbarn zeigen
     */
    private static Map<Object, Set<Object>> getNodes(Set<List<Object>> graph) {
        Map<Object, Set<Object>> nodes = new HashMap<>();

        graph.forEach(edge -> {
            edge.forEach(object -> {
                if (!nodes.containsKey(object)) {
                    nodes.put(object, new HashSet<>());
                }
            });

            edge.forEach(node -> {
                edge.forEach(otherNode -> {
                    if (edge != otherNode) {
                        nodes.get(node).add(otherNode);
                    }
                });
            });
        });
        return nodes;
    }

    /**
     * rechnet die Distanz zwischen source und
     * @param source ist das Objekt, von dem die Suche aus los geht
     * @param destination ist das Objekt, nach dem gesucht wird
     * @param graph Set aus Node Tupeln, zwischen denen Edges verlaufen
     * @return Anzahl an Kanten zwischen source und destination, wenn ein weg gefunden wurde. falls kein weg existiert wird -1 zurückgegeben.
     */
    public static int distance(Object source, Object destination, Set<List<Object>> graph) {
        Map<Object, Set<Object>> nodes = getNodes(graph);
        Map<Object, Integer> distance = new HashMap<>();

        Queue<Object> queue = new LinkedList<>();
        queue.add(source);
        distance.put(source, 0);

        while (!queue.isEmpty()) {
            Object node = queue.poll();
            for (Object neighbor : nodes.get(node)) {
                if (!distance.containsKey(neighbor)) {
                    if (neighbor == destination) {
                        return distance.get(node) + 1;
                    }
                    distance.put(neighbor, distance.get(node) + 1);
                    queue.add(neighbor);
                }
            }
        }
        return -1;
    }
}
