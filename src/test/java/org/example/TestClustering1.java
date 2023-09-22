package org.example;

import org.example.Algorithme.Part3.Clustering;
import org.example.Algorithme.Part3.GraphForClustering;
import org.example.Algorithme.Part3.PrimMST;
import org.example.Readers.ReadFile;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

public class TestClustering1 {
    private Clustering clustering;

    @Before
    public void setUp() throws FileNotFoundException {
        String fileGraph = "src/test/resources/testClustring1.txt";
        ReadFile file = new ReadFile(fileGraph);

        GraphForClustering graph = file.readFileGraphClustering1();

        clustering = new Clustering(graph);
    }

    @Test
    public void testMaxSpacingKClustering() {
        Assert.assertEquals(99,clustering.getMaxSpacingKClustering(4));
    }
}
