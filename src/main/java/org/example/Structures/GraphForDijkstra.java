package org.example.Structures;

import java.util.ArrayList;
import java.util.HashSet;

public class GraphForDijkstra {

    private final ArrayList<ArrayList<int[]>> vertices; // graph vertices


    public GraphForDijkstra(){
        this.vertices = new ArrayList<>();
    }

    public ArrayList<int[]> getVertex(int index) {
        return vertices.get(index - 1);
    }

    public void addVertex() {
        vertices.add(new ArrayList<>());
    }

    public ArrayList<ArrayList<int[]>> getVertices() {
        return vertices;
    }
}
