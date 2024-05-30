package org.example;

import java.util.*;

public class Distance {

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

    public static int distance(Object source, Object destination, Set<List<Object>> graphList) {
        Map<Object, Set<Object>> nodes = getNodes(graphList);
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
