package org.example;

import java.util.*;
import java.util.stream.Collectors;

/**
 * implementiert die Methode distance()
 */
public class Distance {

    /**
     * konvertiert den Graphen von einem Set aus Edges zu einer Map aus Objekten und dazugehörigen Nodes
     *
     * @param graph Set aus Node Tupeln, zwischen denen Edges verlaufen.
     * @return Map aus den Objekten als Keys, und Nodes als deren Values
     */
    private static Map<Object, Node> getNodes(Set<List<Object>> graph) {
        Map<Object, Node> nodes = new HashMap<>();
        graph = graph.stream().filter(Objects::nonNull).collect(Collectors.toSet());

        graph.forEach(edge -> {
            // wenn noch nicht vorhanden werden Nodes erstellt und zur Liste hinzugefügt
            edge.forEach(o -> {
                if (o != null && !nodes.containsKey(o)) {
                    nodes.put(o, new Node(o));
                }
            });

            // null Elemente werden entfernt und Objekte zu ihren entsprechenden Nodes konvertiert
            List<Node> edgeOfNodes = edge.stream().filter(Objects::nonNull).map(nodes::get).toList();

            edgeOfNodes.forEach(node -> node.addNeighbors(edgeOfNodes));
        });
        return nodes;
    }

    /**
     * rechnet die Distanz zwischen source und destination
     *
     * @param source      ist das Objekt, von dem die Suche aus los geht
     * @param destination ist das Objekt, nach dem gesucht wird
     * @param graph       Set aus Node Tupeln, zwischen denen Edges verlaufen
     * @return Anzahl an Kanten zwischen source und destination, wenn ein weg gefunden wurde. falls kein weg existiert wird -1 zurückgegeben.
     */
    public static int distance(Object source, Object destination, Set<List<Object>> graph) {
        Map<Object, Node> nodes = getNodes(graph);

        // immer Nodes mit der kleinsten Distanz sind vorne.
        Queue<Node> queue = new LinkedList<>();

        if (!nodes.containsKey(source)) {
            throw new ObjektDoesNotExistInGraphException("incorrect source: " + source);
        } else if (!nodes.containsKey(destination)) {
            throw new ObjektDoesNotExistInGraphException("incorrect destination: " + destination);
        }

        Node sourceNode = nodes.get(source);
        sourceNode.distance = 0;
        queue.add(sourceNode);

        while (!queue.isEmpty()) {

            // node mit kleinster Distanz
            Node node = queue.poll();
            for (Node neighbor : node.neighbors) {

                // wenn der Nachbar noch nicht marked ist, bekommt er eine Distanz, wird gemarkt und zur Queue hinzugefügt
                if (!neighbor.marked) {
                    // wenn der Nachbar das Ziel ist, wird eine Distanz zurückgegeben
                    if (neighbor.object == destination) {
                        return node.distance + 1;
                    }
                    neighbor.distance = node.distance + 1;
                    neighbor.marked = true;
                    queue.add(neighbor);
                }
            }
        }
        return -1;
    }

    /**
     * Node :)
     */
    private static class Node {
        final Object object;
        /**
         * alle erreichbaren Nachbar nodes
         */
        Set<Node> neighbors;
        /**
         * Distanz zur source Node
         */
        int distance;
        /**
         * wenn marked, ist die Distanz garantiert richtig.
         */
        boolean marked;

        public Node(Object object) {
            this.object = object;
            neighbors = new HashSet<>();
            marked = false;
        }

        /**
         * fügt alle Nodes als Nachbarn hinzu. Null wird ignoriert
         *
         * @param neighbors die dem Set an neighbors hinzugefügt werden soll
         */
        public void addNeighbors(List<Node> neighbors) {
            // fügt alle Nachbarn zur Liste hinzu, die nicht die eigene Node sind.
            neighbors.forEach(node -> {
                if (node != this) {
                    this.neighbors.add(node);
                }
            });
        }
    }
}
