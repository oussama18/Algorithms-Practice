package org.example.Algorithme.Part3;

import java.util.Comparator;
import java.util.List;

public class GreedyScheduleJob {
    public static class Job{
        int length;
        int weight;

        public Job(int length, int weight) {
            this.length = length;
            this.weight = weight;
        }
    }

    private List<Job> jobs;

    public GreedyScheduleJob(List<Job> jobs) {
        this.jobs = jobs;

    }

    private long getSumCompletionWeight(Comparator<? super Job> comparator) {
        jobs.sort(comparator);

        long result = 0;
        long l=0;
        for (Job job : jobs) {
            result += (job.weight * (l+job.length));
            l +=job.length;
        }

        return result;
    }

    private int getDiff(Job a) {
        return a.weight-a.length;
    }

    private float getRatio(Job a) {
        return a.weight/(a.length * 1f);
    }

    public long getSumGreedyScheduleDiff(){
        return getSumCompletionWeight((a,b) -> {
            if (getDiff(a) == getDiff(b)) {
                return Integer.compare(b.weight,a.weight);
            }
            return Integer.compare(getDiff(b),getDiff(a));
        });
    }

    public long getSumGreedyScheduleRation() {
        return getSumCompletionWeight(
                (a,b) -> Float.compare(getRatio(b),getRatio(a))
        );
    }
}
