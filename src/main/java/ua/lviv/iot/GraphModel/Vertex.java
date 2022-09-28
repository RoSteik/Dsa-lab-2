package ua.lviv.iot.GraphModel;

import java.util.LinkedList;

public class Vertex {
    public String key;
    public LinkedList<Vertex> compatibleVertices = new LinkedList<>();

    public Vertex(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return this.key;
    }
}
