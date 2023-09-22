package org.example.Readers;

import org.example.Algorithme.Part3.GraphForClustering;
import org.example.Algorithme.Part3.GreedyScheduleJob;
import org.example.Algorithme.Part3.PrimMST;
import org.example.Structures.Graph;
import org.example.Structures.GraphForDijkstra;

import java.io.*;
import java.util.*;

public class ReadFile {

    public String fileName;

    public ReadFile(String fileName) {
        this.fileName = fileName;
    }

    public List<Graph> readFileToGraphSCC() throws IOException {
        File file = new File(
                fileName);
        BufferedReader br
                = new BufferedReader(new FileReader(file));

        String st;
        // Condition holds true till
        // there is character in a string
        Graph graphDirect = new Graph();
        Graph graphVers = new Graph();
        while ((st = br.readLine()) != null) {
            // Print the string
            String[] nodes = st.split(" ");

            graphDirect.addEdge(nodes[0],nodes[1]);
            graphVers.addEdge(nodes[1],nodes[0]);
        }

        List<Graph> listGraph = new ArrayList<Graph>();
        listGraph.add(graphDirect);
        listGraph.add(graphVers);

        return listGraph;
    }

    public GraphForDijkstra readFileToGraphDijkstra() throws FileNotFoundException {
        GraphForDijkstra graphForDijkstra = new GraphForDijkstra();
        Scanner in = new Scanner(new File(fileName));
        //add all vertices
        while (in.hasNextLine()){
            graphForDijkstra.addVertex();
            String[] line = in.nextLine().split("\t");
            int node = Integer.parseInt(line[0]);
            for (int i = 1; i < line.length; i++){
                String[] edgeStr = line[i].split(",");
                int[] edge = new int[2];
                edge[0] = Integer.parseInt(edgeStr[0]);
                edge[1] = Integer.parseInt(edgeStr[1]);
                graphForDijkstra.getVertex(node).add(edge);
            }
        }
        return graphForDijkstra;
    }

    public List<Integer> readFileToList () throws FileNotFoundException {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Scanner in = new Scanner(new File(fileName));
        //add all vertices
        while (in.hasNextLine()) {
            String line = in.nextLine();
            int number = Integer.parseInt(line);
            list.add(number);
        }

        return list;
    }

    public List<GreedyScheduleJob.Job> readFileToJobs() throws FileNotFoundException {
        Scanner in = new Scanner(new File(fileName));
        int nbJobs = in.nextInt();

        ArrayList<GreedyScheduleJob.Job> list = new ArrayList<>(nbJobs);

        while (in.hasNextInt()) {
            int weight = in.nextInt();
            int length = in.nextInt();
            list.add(new GreedyScheduleJob.Job(length,weight));
        }

        return list;
    }

    public Map<Long,Integer> readFile2Sum() throws FileNotFoundException {
        HashMap<Long, Integer> map = new HashMap<>(100000);
        Scanner in = new Scanner(new File(fileName));

        while (in.hasNextLong()) {
            long number = in.nextLong();
            Integer repeats = map.putIfAbsent(number, 1);
            if (repeats != null)
                map.put(number, repeats+1);
        }

        return map;
    }

    public PrimMST.Graph readFileToSMSTGraph() throws FileNotFoundException {
        Scanner in = new Scanner(new File(fileName));
        int nbV = in.nextInt();
        int nbE = in.nextInt();

        PrimMST.Graph graph = new PrimMST.Graph(nbV, nbE);

        while (in.hasNextInt()) {
            int a = in.nextInt();
            int b = in.nextInt();
            int weight = in.nextInt();
            graph.addEdge(a,b,weight);
        }

        return graph;
    }

    public GraphForClustering readFileGraphClustering1() throws FileNotFoundException {
        Scanner in = new Scanner(new File(fileName));
        int nbNode = in.nextInt();

        GraphForClustering graph = new GraphForClustering(nbNode);

        while (in.hasNextInt()) {
            int a = in.nextInt();
            int b = in.nextInt();
            int dist = in.nextInt();
            graph.addEdge(a,b,dist);
        }

        return graph;
    }
}
