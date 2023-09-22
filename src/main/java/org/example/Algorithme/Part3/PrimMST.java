package org.example.Algorithme.Part3;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class PrimMST {

    public PrimMST(Graph initialGraph) {
        this.initialGraph = initialGraph;
    }

    static class Edge{
        int weight;
        Vertex a,b;

        public Edge(int weight, Vertex a, Vertex b) {
            this.weight = weight;
            this.a = a;
            this.b = b;
        }
    }

    static class Vertex{
        int n;

        boolean explored = false;

        //for heap: to calculate the vertex with the smaller weight
        int key = Integer.MAX_VALUE;

        int vertexWithThisKey;

        public Vertex(int n) {
            this.n = n;
        }

        List<Edge> edgeList = new ArrayList<>();
    }

    public static class Graph {
        Map<Integer,Vertex> listVertex;

        List<Edge> edgeList ;

        public Graph(int nbV, int nbE) {
            listVertex = new HashMap<>(nbV);
            edgeList = new ArrayList<>(nbE);
        }

        public Vertex addVertex(int a) {
            Vertex vertex = listVertex.get(a);

            if(vertex == null){
                vertex= new Vertex(a);
                listVertex.put(a,vertex);
            }

            return vertex;
        }

        public void addEdge(int a, int b, int weight) {
            Vertex va = addVertex(a);
            Vertex vb = addVertex(b);

            Edge e = new Edge(weight,va,vb);

            va.edgeList.add(e);
            vb.edgeList.add(e);
            edgeList.add(e);
        }
    }


    private Graph initialGraph;

    private Vertex getRandomVertex() {
        while (true) {
            int index = ThreadLocalRandom.current().nextInt(1,initialGraph.listVertex.size());
            Vertex vertex = initialGraph.listVertex.get(index);

            if(vertex != null)
                return vertex;
        }
    }

    private PriorityQueue<Vertex> getInitialHeap(Vertex initialVertex) {
        for (Edge edge : initialGraph.edgeList) {
            if (edge.a.equals(initialVertex)) {
                edge.b.key = edge.weight;
                edge.b.vertexWithThisKey = initialVertex.n;
            }
            if(edge.b.equals(initialVertex)) {
                edge.a.key = edge.weight;
                edge.a.vertexWithThisKey = initialVertex.n;
            }
        }

        PriorityQueue<Vertex> vertexPriorityQueue = new PriorityQueue<>(initialGraph.listVertex.size(),
                Comparator.comparingInt(a -> a.key));

        for (Map.Entry<Integer, Vertex> vertex : initialGraph.listVertex.entrySet()) {
            if (!vertex.getValue().equals(initialVertex)) {
                vertexPriorityQueue.add(vertex.getValue());
            }
        }

        return vertexPriorityQueue;
    }

    public Graph getMST() {
        Vertex initialVertex = getRandomVertex();
        initialVertex.explored = true;
        PriorityQueue<Vertex> heapVertex = getInitialHeap(initialVertex);
        Graph spanningGraph = new Graph(initialGraph.listVertex.size(),initialGraph.listVertex.size());

        while (!heapVertex.isEmpty()){
            Vertex ver = heapVertex.poll();
            ver.explored = true;
            spanningGraph.addEdge(ver.n,ver.vertexWithThisKey,ver.key);

            for (Edge edge : ver.edgeList) {
                if (!edge.a.explored) {
                    heapVertex.remove(edge.a);
                    edge.a.key = Math.min(edge.a.key, edge.weight);
                    heapVertex.add(edge.a);
                }
                if (!edge.b.explored) {
                    heapVertex.remove(edge.b);
                    edge.b.key = Math.min(edge.b.key, edge.weight);
                    heapVertex.add(edge.b);
                }
            }
        }

        return spanningGraph;
    }

    public int getSumWeightOfMST() {
        Graph mst = getMST();

        int sum = 0;
        for (Edge edge : mst.edgeList) {
            sum += edge.weight;
        }

        return sum;
    }


}
