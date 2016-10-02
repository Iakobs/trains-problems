package com.jacobibanez;

import com.jacobibanez.model.Graph;

/**
 * @author <a href="mailto=jacobibanez@jacobibanez.com">Jacob Ibáñez Sánchez</a>
 * @since 02/10/16.
 */
public class Main {
    private static final String ROUTE_NOT_FOUND = "NO SUCH ROUTE";
    private static Graph graph;

    public static void main(String... args) {
        graph = new Graph();
        graph.addRoute("A", "B", 5);
        graph.addRoute("B", "C", 4);
        graph.addRoute("C", "D", 8);
        graph.addRoute("D", "C", 8);
        graph.addRoute("D", "E", 6);
        graph.addRoute("A", "D", 5);
        graph.addRoute("C", "E", 2);
        graph.addRoute("E", "B", 3);
        graph.addRoute("A", "E", 7);

        String[][] testBattery = {
                {"A", "B", "C"},
                {"A", "D"},
                {"A", "D", "C"},
                {"A", "E", "B", "C", "D"},
                {"A", "E", "D"},
        };

        int counter = 1;
        for (String[] row : testBattery) {
            printDistance(counter, row);
            counter++;
        }
    }

    private static void printDistance(int iteration, String... stops) {
        Integer distance = graph.getDistance(stops);
        System.out.println("Output #" + iteration + ": " + (distance != null ? distance.toString() : ROUTE_NOT_FOUND));
    }
}
