package com.jacobibanez.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a town
 *
 * @author <a href="mailto=jacobibanez@jacobibanez.com">Jacob Ibáñez Sánchez</a>
 * @since 29/09/16.
 */
public class Node {
    private String name;
    private List<Edge> routes = new ArrayList<>();

    Node(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    List<Edge> getRoutes() {
        return routes;
    }

    void addRoute(Edge route) {
        removeRoute(route);
        routes.add(route);
    }

    void removeRoute(Edge route) {
        if (routes.contains(route)) {
            routes.remove(route);
        }
    }

    Edge getRoute(int position) {
        if (position < routes.size() || position > routes.size()) return null;
        if (routes.size() == 0) return null;
        return routes.get(position);
    }

    Edge getRoute(Edge route) {
        Edge result = null;
        if (routes.contains(route)) {
            result = routes.get(routes.indexOf(route));
        }
        return result;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int hashCode() {
        return 15 * 35 + this.name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Node)) {
            return false;
        } else if (this == obj) {
            return true;
        }
        Node node = (Node) obj;
        return this.name.equals(node.getName());
    }
}
