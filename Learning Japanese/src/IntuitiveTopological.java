import java.util.*;

public class IntuitiveTopological implements TopologicalSort {
    private EditableDiGraph graph;
    private List<Integer> order;
    private boolean isDAG;

    public IntuitiveTopological(EditableDiGraph graph) {
        this.graph = graph;
        order = new LinkedList<>();
        isDAG = true;
        topologicalSort();
    }

    private void topologicalSort() {
        EditableDiGraph copy = new BetterDiGraph();

        while (!copy.isEmpty()) {
            boolean found = false;

            for (int v : copy.vertices()) {
                if (copy.getIndegree(v) == 0) {
                    found = true;
                    order.add(v);
                    for (int w : copy.getAdj(v)) {
                        copy.removeEdge(v, w);
                    }
                    copy.removeVertex(v);
                    break;
                }
            }

            if (!found) {
                isDAG = false;
                break;
            }
        }
    }

    @Override
    public Iterable<Integer> order() {
        return order;
    }

    @Override
    public boolean isDAG() {
        return isDAG;
    }
}