package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DistanceTest {
    Object[] o = new Object[26];
    Set<List<Object>> graph = new HashSet<>();

    private void addEdge(int o1, int o2) {
        List<Object> l = new LinkedList<>();
        l.add(o[o1 % 97]);
        l.add(o[o2 % 97]);
        graph.add(l);
    }

    private int distance(int o1, int o2) {
        return Distance.distance(o[o1 % 97], o[o2 % 97], graph);
    }

    @BeforeEach
    void setUp() {
        for (int i = 0; i < o.length; i++) {
            o[i] = new Object();
        }
    }

    @Test
    void beispiel1() {
        addEdge('a', 'b');
        addEdge('b', 'c');
        assertEquals(2, distance(0, 2));
    }

    @Test
    void beispiel2() {
        addEdge('a', 'b');
        addEdge('c', 'b');
        addEdge('c', 'd');
        addEdge('b', 'd');
        addEdge('e', 'd');
        assertEquals(3, distance('a', 'e'));
    }

    @Test
    void beispiel3() {
        addEdge('a', 'b');
        addEdge('b', 'c');
        addEdge('c', 'd');
        addEdge('b', 'd');
        assertEquals(1, distance('c', 'd'));
    }
}

