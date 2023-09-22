package org.example.Algorithme.Part3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GraphForClustering {

    static class Edge {
        int n1,n2;
        int distance;

        public Edge(int n1, int n2, int distance) {
            this.n1 = n1;
            this.n2 = n2;
            this.distance = distance;
        }
    }

    private List<Edge> edgeList;
    private Set<Integer> nodeSet;

    public GraphForClustering(int nbOfNode) {
        this.edgeList = new ArrayList<>();
        nodeSet = new HashSet<>(nbOfNode);
    }

    public void addEdge(int n1, int n2, int dist) {
        nodeSet.add(n1);
        nodeSet.add(n2);

        edgeList.add(new Edge(n1,n2,dist));
    }

    public List<Edge> getEdgeList() {
        return edgeList;
    }

    public Set<Integer> getNodeSet() {
        return nodeSet;
    }
}
