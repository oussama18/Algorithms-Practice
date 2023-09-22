package org.example;

import org.example.Algorithme.*;
import org.example.Algorithme.Part3.Clustering;
import org.example.Algorithme.Part3.GraphForClustering;
import org.example.Algorithme.Part3.GreedyScheduleJob;
import org.example.Algorithme.Part3.PrimMST;
import org.example.Readers.ReadFile;
import org.example.Structures.Graph;
import org.example.Structures.GraphForDijkstra;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args ) throws IOException {

        String fileGraph = "clustering1.txt";
        ReadFile file = new ReadFile(fileGraph);

        GraphForClustering graph = file.readFileGraphClustering1();

        Clustering clustering = new Clustering(graph);

        System.out.println(clustering.getMaxSpacingKClustering(4));
    }
}
