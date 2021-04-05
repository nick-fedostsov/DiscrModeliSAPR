
import java.util.Map;

public class IsomorphismChecker {
    private final Graph<DirectedGraphNode> graph1 = new Graph<>();
    private final Graph<DirectedGraphNode> graph2 = new Graph<>();

    private final AbstractGraphIsomorphismChecker<DirectedGraphNode>
            checker = new TrivialDirectedGraphIsomorphismChecker();


    private void test() {
        try {
            int firstInputMatrix[][] = MainLogic.create2DIntMatrixFromFile("src\\input\\firstInputMatrix");
            int secondInputMatrix[][] = MainLogic.create2DIntMatrixFromFile("src\\input\\secondInputMatrix");

            DirectedGraphNode[] firstGraphNodes = new DirectedGraphNode[firstInputMatrix.length];
            DirectedGraphNode[] secondGraphNodes = new DirectedGraphNode[secondInputMatrix.length];

            for (int i = 0; i < firstInputMatrix.length; i++) {
                // firstGraphNodes[i] = new DirectedGraphNode(Character.toString((char) (65 + i)) + "1");
                firstGraphNodes[i] = new DirectedGraphNode((i + 1) + Character.toString((char) 65));
                graph1.addNode(firstGraphNodes[i]);
            }

            for (int i = 0; i < firstInputMatrix.length; i++) {
                for (int j = 0; j < firstInputMatrix[i].length; j++) {
                    if (firstInputMatrix[i][j] == 1) {
                        firstGraphNodes[i].addChild(firstGraphNodes[j]);
                    }
                }
            }

            for (int i = 0; i < secondInputMatrix.length; i++) {
                //secondGraphNodes[i] = new DirectedGraphNode(Character.toString((char) (65 + i)) + "2");
                secondGraphNodes[i] = new DirectedGraphNode((i + 1) + Character.toString((char) 66));
                graph2.addNode(secondGraphNodes[i]);
            }

            for (int i = 0; i < secondInputMatrix.length; i++) {
                for (int j = 0; j < secondInputMatrix[i].length; j++) {
                    if (secondInputMatrix[i][j] == 1) {
                        secondGraphNodes[i].addChild(secondGraphNodes[j]);
                    }
                }
            }

            Map<DirectedGraphNode, DirectedGraphNode> isomorphism =
                    checker.getIsomorphism(graph1, graph2);


            if ((isomorphism != null) && (Utils.isIsomorphism(isomorphism))) {
                System.out.println("Графи ізоморфні");
                System.out.println(isomorphism);
            }


        } catch (Exception e) {
            System.out.println("Щось не так!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        IsomorphismChecker var = new IsomorphismChecker();
        var.test();
    }
}
