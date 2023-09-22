package org.example;

import org.example.Readers.ReadFile;
import org.example.Structures.Graph;
import org.example.Structures.GraphForDijkstra;
import org.example.Structures.Node;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


public class TestReadFile {

    @Test
    public void testReadFileSccGraphDirect() throws IOException {
        String fileName = "SCC.txt";

        ReadFile readGraph = new ReadFile(fileName);
        List<Graph> graphs = readGraph.readFileToGraphSCC();

        Assert.assertNotNull("Graph direct est null",graphs.get(0));

        Assert.assertNotNull("la liste des noeuds liées au noeud 2 est vide",
                graphs.get(0).adjacentNode.get(new Node(2)));

        Assert.assertTrue("le nombre de noeud adjacent a 2 doit etre superieur a 1",
                graphs.get(0).adjacentNode.get(new Node(2)).size() > 1);

        Assert.assertEquals("direct graph size error",
                875714,
                graphs.get(0).nbNode );
        Assert.assertEquals("Reverse graph size error",
                875714,
                graphs.get(1).nbNode );

        Assert.assertEquals(47646,
                graphs.get(0).adjacentNode.get(new Node(2)).get(0).label);

    }

    @Test
    public void testReadFileGraphDijkstra() throws FileNotFoundException {
        String filename = "dijkstraData.txt";
        ReadFile readGraph = new ReadFile(filename);
        GraphForDijkstra graphForDijkstra = readGraph.readFileToGraphDijkstra();

        Assert.assertEquals("Number Vertices",200,graphForDijkstra.getVertices().size());

        Assert.assertArrayEquals("a edge in the graph",graphForDijkstra.getVertex(200).get(0),new int []{108,9976});
    }
}
