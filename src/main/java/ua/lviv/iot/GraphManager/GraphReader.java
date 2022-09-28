

package ua.lviv.iot.GraphManager;

import ua.lviv.iot.GraphModel.Graph;
import ua.lviv.iot.GraphModel.Vertex;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GraphReader {

    public Graph setGraphFrom(String path) {

        Graph graph = new Graph();
        String text = getTextFromFile(path);

        setAllVerticesTo(graph, text);

        setToAllVerticesTheirAdjacency(graph, text);


        return graph;

    }

    private void setToAllVerticesTheirAdjacency(Graph graph, String text) {
        Pattern patternForLine = Pattern.compile(":.*;");
        Matcher matcherForLine = patternForLine.matcher(text);
        Pattern patternForVertex = Pattern.compile("\\s[\\w]*[,;]");
        Pattern pattern = Pattern.compile(".*:");
        Matcher matcher = pattern.matcher(text);
        Matcher matcherForVertex;
        String key;

        for (int i = 0; i < graph.adjacencyList.size(); i++) {
            matcher.find();
            matcherForLine.find();
            matcherForVertex = patternForVertex.matcher(matcherForLine.group());

            while (matcherForVertex.find()) {

                key = matcherForVertex.group().substring(1,matcherForVertex.group().length()-1);

                for (Vertex vertex : graph.adjacencyList){
                    if (key.equals(vertex.key)){
                        graph.adjacencyList.get(i).compatibleVertices.add(vertex);
                    }
                }
            }

        }

    }

    private void setAllVerticesTo(Graph graph, String text) {
        Pattern pattern = Pattern.compile(".*:");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            graph.adjacencyList.add(new Vertex(getVertexFrom(matcher.group())));
        }

    }

    public String getVertexFrom(String line) {

        StringBuilder key = new StringBuilder();

        int i = 0;
        while (line.charAt(i) != ':') {
            key.append(line.charAt(i));
            i++;
        }

        return key.toString();

    }

    public static String getTextFromFile(String path) {
        File file = new File(path);
        String text = null;
        try (Scanner scanner = new Scanner(file)) {
            StringBuilder result = new StringBuilder();
            while (scanner.hasNextLine()) {
                result.append(scanner.nextLine()).append("\n");
            }
            text = result.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return text;
    }


}