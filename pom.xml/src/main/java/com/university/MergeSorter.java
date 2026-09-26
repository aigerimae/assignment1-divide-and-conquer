package com.university;

public class MergeSorter {
    private static final int CUTOFF = 15;

    public static long comparisons;
    public static int maxDepth;

    public static void sort(int[] arr) {
        comparisons = 0;
        maxDepth = 0;
        int[] buffer = new int[arr.length];
        sort(arr, buffer, 0, arr.length - 1, 0);
    }

    private static void sort(int[] arr, int[] buffer, int lo, int hi, int depth) {
        if (depth > maxDepth) maxDepth = depth;
        if (hi - lo <= CUTOFF) {
            insertionSort(arr, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(arr, buffer, lo, mid, depth + 1);
        sort(arr, buffer, mid + 1, hi, depth + 1);
        merge(arr, buffer, lo, mid, hi);
    }

    private static void merge(int[] arr, int[] buffer, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) buffer[k] = arr[k];
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) arr[k] = buffer[j++];
            else if (j > hi) arr[k] = buffer[i++];
            else {
                comparisons++;
                if (buffer[j] < buffer[i]) arr[k] = buffer[j++];
                else arr[k] = buffer[i++];
            }
        }
    }

    private static void insertionSort(int[] arr, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= lo && arr[j] > key) {
                comparisons++;
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}