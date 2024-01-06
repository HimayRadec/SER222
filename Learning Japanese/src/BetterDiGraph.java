import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BetterDiGraph implements EditableDiGraph {
    private Map<Integer, Set<Integer>> adjList;
    private Map<Integer, Integer> inDegrees;
    private int vertexCount;
    private int edgeCount;

    public BetterDiGraph() {
        adjList = new HashMap<>();
        inDegrees = new HashMap<>();
        vertexCount = 0;
        edgeCount = 0;
    }

    @Override
    public void addEdge(int v, int w) {
        addVertex(v);
        addVertex(w);

        if (!adjList.get(v).contains(w)) {
            adjList.get(v).add(w);
            inDegrees.put(w, inDegrees.getOrDefault(w, 0) + 1);
            edgeCount++;
        }
    }

    @Override
    public void addVertex(int v) {
        if (!adjList.containsKey(v)) {
            adjList.put(v, new HashSet<>());
            vertexCount++;
        }
    }

    @Override
    public Iterable<Integer> getAdj(int v) {
        return adjList.getOrDefault(v, new HashSet<>());
    }

    @Override
    public int getEdgeCount() {
        return edgeCount;
    }

    @Override
    public int getIndegree(int v) throws NoSuchElementException {
        if (!containsVertex(v)) {
            throw new NoSuchElementException("Vertex does not exist.");
        }
        return inDegrees.getOrDefault(v, 0);
    }

    @Override
    public int getVertexCount() {
        return vertexCount;
    }

    @Override
    public void removeEdge(int v, int w) {
        if (containsVertex(v) && containsVertex(w) && adjList.get(v).contains(w)) {
            adjList.get(v).remove(w);
            inDegrees.put(w, inDegrees.getOrDefault(w, 0) - 1);
            edgeCount--;
        }
    }

    @Override
    public void removeVertex(int v) {
        if (containsVertex(v)) {
            // Remove incoming edges
            for (int u : vertices()) {
                if (adjList.get(u).contains(v)) {
                    adjList.get(u).remove(v);
                    inDegrees.put(v, inDegrees.getOrDefault(v, 0) - 1);
                    edgeCount--;
                }
            }
            // Remove the vertex itself
            adjList.remove(v);
            vertexCount--;
        }
    }

    @Override
    public Iterable<Integer> vertices() {
        return adjList.keySet();
    }

    @Override
    public boolean isEmpty() {
        return vertexCount == 0;
    }

    @Override
    public boolean containsVertex(int v) {
        return adjList.containsKey(v);
    }
}