package org.example;

import org.example.Algorithme.Part3.GreedyScheduleJob;
import org.example.Readers.ReadFile;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

public class ScheduleTest {
    GreedyScheduleJob greedyScheduleJob;

    @Before
    public void setUp() throws FileNotFoundException {
        String fileJobs = "src/test/resources/testSchedule.txt";
        ReadFile file = new ReadFile(fileJobs);

        List<GreedyScheduleJob.Job> jobs = file.readFileToJobs();

        greedyScheduleJob = new GreedyScheduleJob(jobs);

    }

    @Test
    public void testScheduleDiffResult() {
        Assert.assertEquals(68615,greedyScheduleJob.getSumGreedyScheduleDiff());
    }

    @Test
    public void testScheduleRationResult() {
        Assert.assertEquals(67247,greedyScheduleJob.getSumGreedyScheduleRation());
    }
}
