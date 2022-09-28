package ua.lviv.iot.GraphManager;

import ua.lviv.iot.GraphModel.Graph;
import ua.lviv.iot.GraphModel.Vertex;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class CycleDetector {
    boolean visit(Vertex vertex, Set<Vertex> visited, Stack<Vertex> previous) {
        boolean result = false;
        visited.add(vertex);
        for (Vertex adj : vertex.compatibleVertices) {

            if (!adj.equals(previous.peek())) {
                if (visited.contains(adj)) {
                    return true;
                } else {
                    previous.add(vertex);
                    if (visit(adj, visited, previous)) {
                        return true;
                    }
                    previous.pop();
                }
            }
        }

        return result;
    }

    boolean hasCycle(Graph graph) {
        Stack<Vertex> previous = new Stack<>();
        Set<Vertex> visited = new HashSet<>();
        previous.add(null);
        return visit(graph.adjacencyList.get(0), visited, previous);

    }

    public void printCycle(Graph graph) {
        try (FileWriter result = new FileWriter("src/main/resources/result.txt")) {

            if (hasCycle(graph)) {
                result.write("true");
            } else {
                result.write("false");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
