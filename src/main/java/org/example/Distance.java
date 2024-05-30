package org.example;

import java.util.*;

/**
 * implementiert die Methode distance()
 */
public class Distance {

    /**
     * konvertiert den Graphen von einem Set aus Edges zu einer Map aus Objekten und dazugehörigen Nodes
     * @param graph Set aus Node Tupeln, zwischen denen Edges verlaufen.
     * @return Map aus den Objekten als Keys, und Nodes als deren Values
     */
    private static Map<Object, Node> getNodes(Set<List<Object>> graph) {
        Map<Object, Node> nodes = new HashMap<>();

        graph.forEach(edge -> {
            edge.forEach(object -> {
                if (!nodes.containsKey(object)) {
                    nodes.put(object, new Node(object));
                }
            });

            List<Node> edgeOfNodes = edge.stream().map(nodes::get).toList();
            edgeOfNodes.forEach(node -> node.addNeighbors(edgeOfNodes));
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
        Map<Object, Node> nodes = getNodes(graph);

        Queue<Node> queue = new LinkedList<>();

        Node sourceNode = nodes.get(source);
        sourceNode.distance = 0;
        queue.add(sourceNode);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (Node neighbor : node.neighbors) {
                if (!neighbor.marked) {
                    if (neighbor.object == destination) {
                        return node.distance + 1;
                    }
                    neighbor.distance = node.distance + 1;
                    queue.add(neighbor);
                }
            }
        }
        return -1;
    }

    private static class Node {
        final Object object;
        Set<Node> neighbors;
        int distance;
        boolean marked;

        public Node(Object object) {
            this.object = object;
            neighbors = new HashSet<>();
            marked = false;
        }

        public void addNeighbors(List<Node> neighbors){
            neighbors.forEach(node -> {
                if (this != node){
                    this.neighbors.add(node);
                }
            });
        }
    }
}
