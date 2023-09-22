package org.example.Algorithme;

import java.util.*;

public class MedianCalcul {

    private List<Integer> listNumber ;
    private PriorityQueue<Integer> lowerHeap;
    private PriorityQueue<Integer> higherHeap;

    private int median;
    private int nbIteration = 0;

    public MedianCalcul(List<Integer> listNumber) {
        this.listNumber = listNumber;
        lowerHeap = new PriorityQueue<>(Comparator.reverseOrder());
        higherHeap = new PriorityQueue<>(Comparator.naturalOrder());
    }

    private boolean isOddNumber(int i) {
        return i % 2 != 0;
    }

    private int getMedianInIteration(int number) {

        if (nbIteration == 0) {
            nbIteration ++;
            higherHeap.add(number);
            lowerHeap.add(number);
            median = number;
            return number;
        }

        if (number < median) {
            lowerHeap.add(number);

            if (lowerHeap.size() > higherHeap.size()) {
                lowerHeap.poll();
                median = lowerHeap.peek();
                higherHeap.add(median);
            }
        }
        else {
            higherHeap.add(number);

            if (lowerHeap.size() + 1 < higherHeap.size()) {
                higherHeap.poll();
                median = higherHeap.peek();
                lowerHeap.add(median);
            }
        }

        nbIteration++;
        return median;
    }

    public int getModuloMedianSumForEveryIteration() {
        long sumMedian = 0;

        for (Integer number : listNumber) {
            sumMedian += getMedianInIteration(number);
        }

        return (int) (sumMedian % 10000);
    }

}
