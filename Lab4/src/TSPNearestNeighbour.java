import java.util.Stack;

public class TSPNearestNeighbour {
    private int numberOfNodes;
    private Stack<Integer> stack;

    private  TSPNearestNeighbour() {
        stack = new Stack<Integer>();
    }

    private void tsp(int adjacencyMatrix[][]) {
        numberOfNodes = adjacencyMatrix[1].length - 1;
        int[] visited = new int[numberOfNodes + 1];
        visited[1] = 1;
        stack.push(1);
        int element, dst = 0, i;
        int min = Integer.MAX_VALUE;
        boolean minFlag = false;
        System.out.print(1 + "\t");

        while (!stack.isEmpty()) {
            element = stack.peek();
            i = 1;
            min = Integer.MAX_VALUE;
            while (i <= numberOfNodes) {
                if (adjacencyMatrix[element][i] >= 1 && visited[i] == 0) {
                    if (min > adjacencyMatrix[element][i]) {
                        min = adjacencyMatrix[element][i];
                        dst = i;
                        minFlag = true;
                    }
                }
                i++;
            }
            if (minFlag) {
                visited[dst] = 1;
                stack.push(dst);
                System.out.print(dst + "\t");
                minFlag = false;
                continue;
            }
            stack.pop();
        }
    }

    public static void main(String... arg) {
        int number_of_nodes;

        try {
            int adjacency_matrix[][] = MainLogic.create2DIntMatrixFromFile("src\\files\\martix2");

            number_of_nodes = adjacency_matrix.length - 1;


            for (int i = 1; i <= number_of_nodes; i++) {
                for (int j = 1; j <= number_of_nodes; j++) {
                    if (adjacency_matrix[i][j] == 1 && adjacency_matrix[j][i] == 0) {
                        adjacency_matrix[j][i] = 1;
                    }
                }
            }
            System.out.println("Міста відвідані у такому порядку: ");
            TSPNearestNeighbour tspNearestNeighbour = new TSPNearestNeighbour();
            tspNearestNeighbour.tsp(adjacency_matrix);
        } catch (Exception e) {
            System.out.println("Щось не так!");
            e.printStackTrace();
        }
    }
}

