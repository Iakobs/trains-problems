package com.jacobibanez.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A class representing a graph, which is responsible for dealing with nodes and edges. This class also gives a proper
 * interface to the user in order to make some calculations relevant to the model
 *
 * @author <a href="mailto=jacobibanez@jacobibanez.com">Jacob Ibáñez Sánchez</a>
 * @since 29/09/16.
 */
public class Graph {
    private Map<String, Node> neighbourhood = new HashMap<>();

    private void addNode(String name) {
        if (neighbourhood.containsKey(name)) {
            return;
        }
        neighbourhood.put(name, new Node(name));
    }

    /**
     * Adds a new relation between two nodes to the graph. If the origin and the destination are the same, or if
     * the weight is null, it does not add anything
     *
     * @param originName      Name to identify the origin Node
     * @param destinationName Name to identify the destination Node
     * @param weight          Weight of the relation between the nodes
     */
    public void addRoute(String originName, String destinationName, Integer weight) {
        if (originName.equals(destinationName)) return;
        if (weight == null) return;

        addNode(originName);
        addNode(destinationName);
        Node origin = neighbourhood.get(originName);
        Node destination = neighbourhood.get(destinationName);
        Edge edge = new Edge(origin, destination, weight);
        origin.addRoute(edge);
    }

    private Integer getDistance(String originName, String destinationName) {
        if (!neighbourhood.containsKey(originName)) return null;
        if (!neighbourhood.containsKey(destinationName)) return null;
        return getShortestDistance(originName, destinationName);
    }

    /**
     * Gets the minimum distance between two nodes, passing by all intermediate nodes if any.
     *
     * @param stops Ordered from oritgin to destination collection of nodes which distance we need to retrieve
     * @return The total minimum distance between all nodes
     */
    public Integer getDistance(String... stops) {
        if (stops.length < 2) return null;
        Integer totalDistance = 0;
        Integer actualDistance;
        for (int i = 0; i < stops.length - 1; i++) {
            actualDistance = getDistance(stops[i], stops[i + 1]);
            if (actualDistance == null) {
                totalDistance = null;
                break;
            }
            totalDistance += actualDistance;
        }
        return totalDistance;
    }

    /**
     * Gets a collection of available routes between two nodes
     *
     * @param originName      Name to identify the origin Node
     * @param destinationName Name to identify the destination Node
     * @return A collection of edges
     */
    public List<Edge> getRoutes(String originName, String destinationName) {
        if (!neighbourhood.containsKey(originName)) return null;
        if (!neighbourhood.containsKey(destinationName)) return null;
        Node origin = neighbourhood.get(originName);
        Node destination = neighbourhood.get(destinationName);
        return origin.getRoutes().stream()
                .filter(edge -> edge.getDestination().equals(destination))
                .collect(Collectors.toList());
    }

    /**
     * Returns the minimum distance between two nodes
     *
     * @param origin      Name to identify the origin Node
     * @param destination Name to identify the destination Node
     * @return Minimum distance between two nodes if any. Null otherwise
     */
    public Integer getShortestDistance(String origin, String destination) {
        return getRoutes(origin, destination).stream()
                .map(Edge::getWeight)
                .min(Integer::compareTo)
                .orElse(null);
    }

    /**
     * Get a collection of nodes which origin is a given node
     *
     * @param originName Name to identify the origin Node
     * @return A collection of nodes
     */
    public List<Node> getDestinations(String originName) {
        if (!neighbourhood.containsKey(originName)) return null;
        List<Node> destinations = new ArrayList<>();
        Node origin = neighbourhood.get(originName);
        destinations.addAll(origin.getRoutes().stream()
                .map(Edge::getDestination)
                .collect(Collectors.toList()));
        return destinations;
    }

    /**
     * Get a collection of nodes from where you can end into the given node
     *
     * @param destinationName Name to identify the destination Node
     * @return A collection of nodes
     */
    public List<Node> getOrigins(String destinationName) {
        if (!neighbourhood.containsKey(destinationName)) return null;
        List<Node> origins = new ArrayList<>();
        Node destination = neighbourhood.get(destinationName);
        for (Node neighbour : neighbourhood.values()) {
            if (!neighbour.equals(destination)) {
                origins.addAll(neighbour.getRoutes().stream()
                        .filter(route -> route.getDestination().equals(destination))
                        .map(route -> neighbour)
                        .collect(Collectors.toList()));
            }
        }
        return origins;
    }
}
