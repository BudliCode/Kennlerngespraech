package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        Object d = new Object();
        Object e = new Object();

        Set<List<Object>> graph = new HashSet<>();
        List<Object> ab = new LinkedList<>();
        ab.add(a);
        ab.add(b);

        List<Object> bc = new LinkedList<>();
        bc.add(b);
        bc.add(c);

        List<Object> cb = new LinkedList<>();
        cb.add(c);
        cb.add(b);

        List<Object> cd = new LinkedList<>();
        cd.add(c);
        cd.add(d);

        List<Object> bd = new LinkedList<>();
        bd.add(b);
        bd.add(d);

        List<Object> ed = new LinkedList<>();
        ed.add(e);
        ed.add(d);

        graph.add(ab);
        graph.add(bc);

        System.out.println(Distance.distance(a, c, graph));

        graph.clear();
        graph.add(ab);
        graph.add(cb);
        graph.add(cd);
        graph.add(bd);
        graph.add(ed);

        System.out.println(Distance.distance(a , e, graph));

        graph.clear();
        graph.add(ab);
        graph.add(bc);
        graph.add(cd);
        graph.add(bd);

        System.out.println(Distance.distance(c , d, graph));
    }
}