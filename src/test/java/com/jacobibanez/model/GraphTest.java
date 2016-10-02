package com.jacobibanez.model;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * This test is a WhiteBox test for the Graph class, checking all functionalities work as expected
 *
 * @author <a href="mailto=jacobibanez@jacobibanez.com">Jacob Ibáñez Sánchez</a>
 * @since 02/10/16.
 */
public class GraphTest {
    private Graph graph;

    @Before
    public void setUp() {
        graph = new Graph();
    }

    @After
    public void clean() {
        graph = null;
    }

    @Test
    public void testGetDifferentRoutesBetweenTowns() {
        graph.addRoute("A", "B", 5);
        graph.addRoute("A", "B", 6);
        graph.addRoute("B", "A", 12);
        graph.addRoute("A", "C", 4);
        assertEquals(2, graph.getRoutes("A", "B").size());
    }

    @Test
    public void testGetShortestDistanceBetweenTowns() {
        graph.addRoute("A", "B", 5);
        graph.addRoute("A", "B", 6);
        assertEquals(new Integer(5), graph.getDistance("A", "B"));

        graph.addRoute("B", "C", 12);
        graph.addRoute("B", "C", 11);
        assertEquals(new Integer(16), graph.getDistance("A", "B", "C"));

        graph.addRoute("A", "C", 3);
        assertEquals(null, graph.getDistance("A", "C", "B"));
    }

    @Test
    public void testAddRoute() {
        graph.addRoute("A", "B", null);
        assertEquals(null, graph.getDestinations("A"));
        assertEquals(null, graph.getOrigins("B"));

        graph.addRoute("A", "A", 0);
        assertEquals(null, graph.getDestinations("A"));
        assertEquals(null, graph.getOrigins("B"));
    }
}
