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
        assertEquals(2, distance('a', 'c'));
    }

    @Test
    void beispiel2() {
        String hey = "hey";
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

    @Test
    void wegExistiertNicht(){

    }

    @Test
    void exceptions(){
        addEdge(0,1);
        addEdge(1,2);
        assertThrows(ObjektDoesNotExistInGraphException.class, () -> distance(1,3));
        assertThrows(ObjektDoesNotExistInGraphException.class, () -> distance(3,1));
    }

    @Test
    void mehrereEdges(){
        graph.add(List.of(o[0], o[1], o[2]));
        assertEquals(1, distance(0,1));
        assertEquals(1, distance(1,2));
        assertEquals(1, distance(2,0));
    }

    @Test
    void mitNullWertenAlsNode(){
        Object[] arr = {null, o[0]};
        graph.add(Arrays.asList(arr));
        arr = new Object[]{o[0], null, o[1]};
        graph.add(Arrays.asList(arr));
        assertEquals(1, distance(0, 1));
    }

    @Test
    void mitNullwertenAlsEdge(){
        addEdge(0,1);
        graph.add(null);
        addEdge(1,2);
        assertEquals(1, distance(1,2));
    }

}

