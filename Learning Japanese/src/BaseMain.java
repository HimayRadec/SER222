import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BaseMain {
    public static void main(String[] args) {
        EditableDiGraph graph = new BetterDiGraph();
        Map<Integer, String> idToKanji = new HashMap<>();

        try {
            // Read data-kanji.txt to populate graph and idToKanji map
            BufferedReader kanjiReader = new BufferedReader(new FileReader("src/data-kanji.txt"));
            String line;
            while ((line = kanjiReader.readLine()) != null) {
                if (line.trim().startsWith("#")) {
                    continue;
                }
                String[] parts = line.split("\\s+");
                int id = Integer.parseInt(parts[0]);
                String kanji = parts[1];
                graph.addVertex(id);
                idToKanji.put(id, kanji);
            }

            kanjiReader.close();

            // Read data-components.txt to add edges to the graph
            BufferedReader componentsReader = new BufferedReader(new FileReader("data-components.txt"));
            while ((line = componentsReader.readLine()) != null) {
                String[] parts = line.split("\\s+");
                int src = Integer.parseInt(parts[0]);
                int dst = Integer.parseInt(parts[1]);
                graph.addEdge(src, dst);
            }
            componentsReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Perform topological sort
        TopologicalSort topologicalSort = new IntuitiveTopological(graph);

        // Display the characters in the ordering
        for (int id : topologicalSort.order()) {
            System.out.println(idToKanji.get(id));
        }
    }
}