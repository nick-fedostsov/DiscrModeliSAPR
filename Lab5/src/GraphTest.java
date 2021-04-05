import java.util.Iterator;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class GraphTest {

    private final Graph<DirectedGraphNode> graph = new Graph<>();
    private final DirectedGraphNode a = new DirectedGraphNode("A");
    private final DirectedGraphNode b = new DirectedGraphNode("B");
    private final DirectedGraphNode c = new DirectedGraphNode("C");
    private final DirectedGraphNode d = new DirectedGraphNode("D");
    private final DirectedGraphNode e = new DirectedGraphNode("E");

    @Before
    public void before() {
        graph.clear();
    }

    @Test
    public void test() {
        graph.addNode(a);
        graph.addNode(e);
        graph.addNode(d);

        assertEquals(3, graph.size());

        Iterator<DirectedGraphNode> iterator = graph.iterator();

        assertEquals(a, iterator.next());
        assertEquals(e, iterator.next());
        assertEquals(d, iterator.next());
        assertFalse(iterator.hasNext());

        assertTrue(graph.getNodeByName("A").equals(a));
        assertTrue(graph.getNodeByName("E").equals(e));
        assertTrue(graph.getNodeByName("D").equals(d));
        assertTrue(graph.getNodeByName("B") == null);

        a.addChild(e);
        e.addChild(d);
        d.addChild(e);

        assertEquals(3, graph.getNumberOfEdges());

        Graph<DirectedGraphNode> anotherGraph = new Graph<>();

        anotherGraph.addNode(a);

        assertEquals(1, anotherGraph.size());
        assertEquals(0, anotherGraph.getNumberOfEdges());

        assertEquals(anotherGraph, a.getOwnerGraph());
        assertEquals(graph, d.getOwnerGraph());
        assertEquals(graph, e.getOwnerGraph());

        assertEquals(2, graph.size());
        assertEquals(2, graph.getNumberOfEdges());

        graph.removeNode(e);
        d.addChild(d);

        assertEquals(1, graph.size());
        assertEquals(1, graph.getNumberOfEdges());

        assertEquals(d, graph.getNodeByName("D"));
    }
}