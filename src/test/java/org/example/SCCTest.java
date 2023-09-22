package org.example;


import org.example.Algorithme.KosarajuSCC;
import org.example.Readers.ReadFile;
import org.example.Structures.Graph;
import org.example.Structures.Node;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SCCTest {

    private String fileGraph = "src/test/resources/TestKosaraju.txt";
    private Graph graphDirect;
    private Graph graphVer;

    @Before
    public void setUp() throws IOException {
        ReadFile readGraph = new ReadFile(fileGraph);
        List<Graph> graphs = readGraph.readFileToGraphSCC();
        graphDirect = graphs.get(0);
        graphVer = graphs.get(1);
    }

    @Test
    public void testgetNodeIfExistGraphClass(){
        Assert.assertNotNull(graphDirect.getNodeIfExist(9L));
    }

    @Test
    public void testGetEdgesOutOfInGraphClassInDirectGraph(){
        Node node = graphDirect.getNodeIfExist(9);

        Assert.assertNotNull(node);

        Assert.assertEquals(2,
                graphDirect.getEdgeOutOf(node).size());
    }

    @Test
    public void testGetEdgesOutOfInGraphClassInReversedGraph(){
        Node node = graphVer.getNodeIfExist(9);

        Assert.assertNotNull(node);

        Assert.assertEquals(1,
                graphVer.getEdgeOutOf(node).size());
    }



    @Test
    public void testKosarajuAlgo() {
        KosarajuSCC kosarajuSCC = new KosarajuSCC(graphDirect, graphVer);

        long[] maxFiveComponentSize = kosarajuSCC.ListOfMaxFiveComponentSize();

        Assert.assertEquals(9,kosarajuSCC.getTime());

        Assert.assertArrayEquals(
                new long[]{3L, 3L, 3L, 0L, 0L},
                maxFiveComponentSize
        );

    }

//    @Test
//    public void testKosarajuAlgo1() {
//        KosarajuSCC kosarajuSCC = new KosarajuSCC(graphDirect, graphVer);
//
//        long[] maxFiveComponentSize = kosarajuSCC.ListOfMaxFiveComponentSize();
//
//        Assert.assertEquals(8,kosarajuSCC.getTime());
//
//        Assert.assertArrayEquals(
//                new long[]{3L, 3L, 2L, 0L, 0L},
//                maxFiveComponentSize
//        );
//
//    }
}
