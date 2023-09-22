package org.example.Algorithme;

import org.example.Structures.GraphForDijkstra;

import java.util.HashSet;

public class DijkstraAlgo {


    private HashSet<Integer> explored; // nodes that have been explored;

    private final GraphForDijkstra graphForDijkstra;

    public DijkstraAlgo(GraphForDijkstra graphForDijkstra) {
        this.graphForDijkstra = graphForDijkstra;
    }

    private int[] shortestPath(){
        int n = graphForDijkstra.getVertices().size();
        explored = new HashSet<Integer>();
        int[] paths = new int[n];
        explored.add(1);
        paths[0] = 0;
        while (explored.size() < n){
            int w = -1;
            int l = 1000000;
            for (int node : explored){
                for (int[] edge : graphForDijkstra.getVertex(node)){
                    if (!explored.contains(edge[0])){
                        if (paths[node-1] + edge[1] < l){
                            w = edge[0];
                            l = paths[node-1] + edge[1];
                        }
                    }
                }
            }
            if (w != -1){
                explored.add(w);
                paths[w-1] = l;
                //System.out.println(w + " " + l);
            } else {
                for (int i = 0; i < n; i++){
                    if (!explored.contains(i+1)){
                        paths[i] = 1000000;
                    }
                }
                break;
            }
        }
        return paths;
    }

    public int [] getPathDistanceFor(int... vertices) {
        int[] paths = shortestPath();

        int[] pathDistance = new int[vertices.length];

        int i=0;
        for (int vertex : vertices) {
            pathDistance[i] = paths[vertex-1];
            i++;
        }

        return pathDistance;
    }
}
