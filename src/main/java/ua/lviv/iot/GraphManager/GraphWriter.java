package ua.lviv.iot.GraphManager;
import ua.lviv.iot.GraphModel.Graph;
import java.io.FileWriter;
import java.io.IOException;
import ua.lviv.iot.GraphModel.Vertex;


public class GraphWriter {

    public void writeToFile(Graph graph) {
        try (FileWriter result = new FileWriter("src/main/resources/result.txt")) {

            for (Vertex element : graph.adjacencyList) {
                result.write(element.key.toString()+ ": ");
                for (int i = 0; i < element.compatibleVertices.size(); i++){

                    if (i == element.compatibleVertices.size()-1){
                        result.write(element.compatibleVertices.get(i).toString());
                    } else {
                        result.write(element.compatibleVertices.get(i)+ ", ");
                    }
                }

                result.write(";\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}