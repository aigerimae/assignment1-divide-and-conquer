package com.university;

import java.util.Random;

public class Experiment {

    public enum InputType { RANDOM, SORTED, REVERSE_SORTED, DUPLICATE_HEAVY }

    private static final Random RANDOM = new Random();

    public static int[] generate(int n, InputType type) {
        int[] arr = new int[n];
        switch (type) {
            case RANDOM:
                for (int i = 0; i < n; i++) arr[i] = RANDOM.nextInt(1_000_000);
                break;
            case SORTED:
                for (int i = 0; i < n; i++) arr[i] = i;
                break;
            case REVERSE_SORTED:
                for (int i = 0; i < n; i++) arr[i] = n - i;
                break;
            case DUPLICATE_HEAVY:
                for (int i = 0; i < n; i++) arr[i] = RANDOM.nextInt(10);
                break;
        }
        return arr;
    }

    public static long timeMergeSort(int[] arr) {
        int[] copy = arr.clone();
        long start = System.nanoTime();
        MergeSorter.sort(copy);
        return System.nanoTime() - start;
    }

    public static long timeQuickSort(int[] arr) {
        int[] copy = arr.clone();
        long start = System.nanoTime();
        QuickSorter.sort(copy);
        return System.nanoTime() - start;
    }

    public static long timeSelect(int[] arr, int k) {
        int[] copy = arr.clone();
        long start = System.nanoTime();
        DeterministicSelector.select(copy, k);
        return System.nanoTime() - start;
    }

    public static Point[] generatePoints(int n) {
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point(RANDOM.nextDouble() * 1_000_000, RANDOM.nextDouble() * 1_000_000);
        }
        return points;
    }

    public static long timeClosestPair(Point[] points) {
        long start = System.nanoTime();
        ClosestPairSolver.closestPair(points);
        return System.nanoTime() - start;
    }
}