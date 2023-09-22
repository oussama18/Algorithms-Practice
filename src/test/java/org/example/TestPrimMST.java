package org.example;

import org.example.Algorithme.Part3.GreedyScheduleJob;
import org.example.Algorithme.Part3.PrimMST;
import org.example.Readers.ReadFile;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

public class TestPrimMST {


    private PrimMST primMst;

    @Before
    public void setUp() throws FileNotFoundException {
        String fileJobs = "src/test/resources/testSpaningMST.txt";
        ReadFile file = new ReadFile(fileJobs);

        PrimMST.Graph graph = file.readFileToSMSTGraph();

        primMst = new PrimMST(graph);
    }

    @Test
    public void testPrimMstWithSumOfWeight() {
        Assert.assertEquals(7,primMst.getSumWeightOfMST());
    }
}
