import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class TrivialDirectedGraphIsomorphismTesterTest {

    private final DirectedGraphNode a1 = new DirectedGraphNode("A1");
    private final DirectedGraphNode b1 = new DirectedGraphNode("B1");
    private final DirectedGraphNode c1 = new DirectedGraphNode("C1");
    private final DirectedGraphNode d1 = new DirectedGraphNode("D1");
    private final DirectedGraphNode e1 = new DirectedGraphNode("E1");
    private final DirectedGraphNode f1 = new DirectedGraphNode("F1");
    private final DirectedGraphNode g1 = new DirectedGraphNode("G1");
    private final DirectedGraphNode h1 = new DirectedGraphNode("H1");

    private final DirectedGraphNode a2 = new DirectedGraphNode("A2");
    private final DirectedGraphNode b2 = new DirectedGraphNode("B2");
    private final DirectedGraphNode c2 = new DirectedGraphNode("C2");
    private final DirectedGraphNode d2 = new DirectedGraphNode("D2");
    private final DirectedGraphNode e2 = new DirectedGraphNode("E2");
    private final DirectedGraphNode f2 = new DirectedGraphNode("F2");
    private final DirectedGraphNode g2 = new DirectedGraphNode("G2");
    private final DirectedGraphNode h2 = new DirectedGraphNode("H2");

    private final Graph<DirectedGraphNode> graph1 = new Graph<>();
    private final Graph<DirectedGraphNode> graph2 = new Graph<>();

    private final AbstractGraphIsomorphismChecker<DirectedGraphNode>
            checker = new TrivialDirectedGraphIsomorphismChecker();

    @Before
    public void before() {
        graph1.clear();
        graph2.clear();
    }

    @Test
    public void testGetIsomorphism1() {
        graph1.addNode(a1);
        graph1.addNode(b1);
        graph1.addNode(c1);

        graph2.addNode(a2);
        graph2.addNode(b2);

        a1.addChild(c1);
        a2.addChild(c2);

        assertNull(checker.getIsomorphism(graph1, graph2));
    }

    @Test
    public void testGetIsomorphism2() {
        graph1.addNode(a1);
        graph1.addNode(b1);
        graph1.addNode(c1);

        graph2.addNode(a2);
        graph2.addNode(b2);
        graph2.addNode(e2);

        a1.addChild(b1);
        a2.addChild(e2);

        Map<DirectedGraphNode, DirectedGraphNode> isomorphism =
                checker.getIsomorphism(graph1, graph2);

        assertNotNull(isomorphism);
        assertTrue(Utils.isIsomorphism(isomorphism));

    }

    @Test
    public void testGetIsomorphism3() {
        graph1.addNode(a1);
        graph1.addNode(b1);
        graph1.addNode(c1);

        graph2.addNode(a2);
        graph2.addNode(b2);
        graph2.addNode(e2);

        a1.addChild(b1);
        b1.addChild(c1);
        a2.addChild(e2);

        assertNull(checker.getIsomorphism(graph1, graph2));

    }

    @Test
    public void testGetIsomorphism4() {
        //       c - e
        //      /   / \
        // a - b    |  g - h
        //      \  /  /
        //       d - f
        // Directed edges from nodes with smaller lexicographic name to larger.

        graph1.addNode(a1);
        graph1.addNode(b1);
        graph1.addNode(c1);
        graph1.addNode(d1);
        graph1.addNode(e1);
        graph1.addNode(f1);
        graph1.addNode(g1);
        graph1.addNode(h1);

        a1.addChild(b1);
        b1.addChild(c1);
        b1.addChild(d1);
        c1.addChild(e1);
        d1.addChild(f1);
        d1.addChild(e1);
        e1.addChild(g1);
        f1.addChild(g1);
        g1.addChild(h1);

        graph2.addNode(h2);
        graph2.addNode(b2);
        graph2.addNode(c2);
        graph2.addNode(d2);
        graph2.addNode(e2);
        graph2.addNode(f2);
        graph2.addNode(g2);
        graph2.addNode(a2);

        h2.addChild(b2);
        b2.addChild(c2);
        b2.addChild(d2);
        c2.addChild(e2);
        d2.addChild(f2);
        d2.addChild(e2);
        e2.addChild(g2);
        f2.addChild(g2);
        g2.addChild(a2);

        Map<DirectedGraphNode, DirectedGraphNode> isomorphism =
                checker.getIsomorphism(graph1, graph2);

        assertNotNull(isomorphism);
        assertTrue(Utils.isIsomorphism(isomorphism));
    }
}