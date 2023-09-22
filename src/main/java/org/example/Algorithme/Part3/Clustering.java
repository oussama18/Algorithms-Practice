package org.example.Algorithme.Part3;

import org.jgrapht.alg.util.UnionFind;

import java.util.Comparator;

public class Clustering {
    private UnionFind<Integer> nodeUnionFind;
    private GraphForClustering graph;

    public Clustering(GraphForClustering graph) {
        this.graph = graph;
        nodeUnionFind = new UnionFind<>(graph.getNodeSet());
    }

    public int getMaxSpacingKClustering(int k) {
        int actualK = graph.getNodeSet().size();
        graph.getEdgeList().sort(Comparator.comparingInt(a -> a.distance));

        for (GraphForClustering.Edge edge : graph.getEdgeList()) {
            if (!nodeUnionFind.inSameSet(edge.n1, edge.n2)) {
                if (actualK == k)
                    return edge.distance;

                nodeUnionFind.union(edge.n1, edge.n2);
                actualK--;
            }

        }

        return 0;
    }
}
