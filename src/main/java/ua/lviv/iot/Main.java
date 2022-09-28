package ua.lviv.iot;
import ua.lviv.iot.GraphManager.CycleDetector;
import ua.lviv.iot.GraphModel.Graph;
import ua.lviv.iot.GraphManager.GraphReader;

public class Main {

    public static void main(String[] args) {

        GraphReader reader = new GraphReader();
        Graph graph = reader.setGraphFrom("src/main/resources/input.txt");

        CycleDetector cycleDetector = new CycleDetector();

        cycleDetector.printCycle(graph);



    }




}
