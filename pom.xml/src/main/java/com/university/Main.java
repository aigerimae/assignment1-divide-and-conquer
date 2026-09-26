package com.university;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) throws IOException {
        int[] sizes = {100, 1000, 10000, 100000};
        Experiment.InputType[] types = Experiment.InputType.values();

        try (PrintWriter writer = new PrintWriter(new FileWriter("results/results.csv"))) {
            writer.println("algorithm,input_type,n,time_ns,max_depth,comparisons");

            for (int n : sizes) {
                for (Experiment.InputType type : types) {
                    int[] data = Experiment.generate(n, type);

                    long mergeTime = Experiment.timeMergeSort(data);
                    writer.printf("MergeSort,%s,%d,%d,%d,%d%n",
                            type, n, mergeTime, MergeSorter.maxDepth, MergeSorter.comparisons);

                    long quickTime = Experiment.timeQuickSort(data);
                    writer.printf("QuickSort,%s,%d,%d,%d,%d%n",
                            type, n, quickTime, QuickSorter.maxDepth, QuickSorter.comparisons);

                    System.out.printf("Done: n=%d, type=%s%n", n, type);
                }
            }
        }

        System.out.println("Results saved to results/results.csv");
    }
}