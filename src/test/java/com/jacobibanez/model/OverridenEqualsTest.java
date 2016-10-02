package com.jacobibanez.model;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * This test checks overriden equals methods in the model
 *
 * @author <a href="mailto=jacobibanez@jacobibanez.com">Jacob Ibáñez Sánchez</a>
 * @since 29/09/16.
 */
public class OverridenEqualsTest {
    private Node nodeA;
    private Node nodeB;
    private Edge edge;

    @Before
    public void setUp() {
        nodeA = new Node("A");
        nodeB = new Node("B");
        edge = new Edge(nodeA, nodeB, 5);
    }

    @After
    public void clean() {
        nodeA = null;
        nodeB = null;
        edge = null;
    }

    @Test
    public void testEqualsNodes() {
        assertEquals(new Node("A"), nodeA);
        assertEquals(nodeB, nodeB);
        assertEquals(false, nodeB == new Node("B"));
    }

    @Test
    public void testEqualsEdges() {
        Edge testEdge = new Edge(nodeA, nodeB, 5);
        assertEquals(testEdge, edge);
        assertEquals(testEdge, testEdge);
        assertEquals(false, testEdge == edge);
        assertEquals(false, testEdge.equals(new Edge(nodeA, nodeB, 6)));
    }
}
