package com.jacobibanez.model;

/**
 * A class representing a route between two towns
 *
 * @author <a href="mailto=jacobibanez@jacobibanez.com">Jacob Ibáñez Sánchez</a>
 * @since 29/09/16.
 */
public class Edge {
    private Node origin;
    private Node destination;
    private Integer weight;

    Edge(Node origin, Node destination, int weight) {
        this.origin = origin;
        this.destination = destination;
        this.weight = weight;
    }

    Node getOrigin() {
        return origin;
    }

    Node getDestination() {
        return destination;
    }

    /**
     * The distance between two towns
     *
     * @return The actual distance
     */
    public Integer getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return this.origin.toString() + this.destination.toString() + this.weight;
    }

    @Override
    public int hashCode() {
        return 17 * 37 + this.origin.hashCode() + this.destination.hashCode() + this.weight.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Edge)) {
            return false;
        } else if (this == obj) {
            return true;
        }
        Edge edge = (Edge) obj;
        return this.origin.equals(edge.getOrigin()) &&
                this.destination.equals(edge.getDestination()) &&
                this.weight.equals(edge.getWeight());
    }
}
