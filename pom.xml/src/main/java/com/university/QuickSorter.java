package com.university;

import java.util.Random;

public class QuickSorter {
    private static final Random RANDOM = new Random();

    public static long comparisons;
    public static int maxDepth;

    public static void sort(int[] arr) {
        comparisons = 0;
        maxDepth = 0;
        sort(arr, 0, arr.length - 1, 0);
    }

    private static void sort(int[] arr, int lo, int hi, int depth) {
        if (depth > maxDepth) maxDepth = depth;
        while (lo < hi) {
            int p = partition(arr, lo, hi);
            if (p - lo < hi - p) {
                sort(arr, lo, p - 1, depth + 1);
                lo = p + 1;
            } else {
                sort(arr, p + 1, hi, depth + 1);
                hi = p - 1;
            }
        }
    }

    private static int partition(int[] arr, int lo, int hi) {
        int pivotIndex = lo + RANDOM.nextInt(hi - lo + 1);
        swap(arr, pivotIndex, hi);
        int pivot = arr[hi];
        int i = lo;
        for (int j = lo; j < hi; j++) {
            comparisons++;
            if (arr[j] < pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, hi);
        return i;
    }

    private static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}