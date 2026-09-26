package com.university;

import java.util.Arrays;

public class DeterministicSelector {

    public static int select(int[] arr, int k) {
        return select(arr, 0, arr.length - 1, k);
    }

    private static int select(int[] arr, int lo, int hi, int k) {
        if (lo == hi) return arr[lo];

        int pivot = medianOfMedians(arr, lo, hi);
        int pivotIndex = partitionAroundValue(arr, lo, hi, pivot);

        if (k == pivotIndex) return arr[k];
        else if (k < pivotIndex) return select(arr, lo, pivotIndex - 1, k);
        else return select(arr, pivotIndex + 1, hi, k);
    }

    private static int medianOfMedians(int[] arr, int lo, int hi) {
        int n = hi - lo + 1;
        if (n <= 5) {
            Arrays.sort(arr, lo, hi + 1);
            return arr[lo + n / 2];
        }
        int numGroups = (n + 4) / 5;
        int[] medians = new int[numGroups];
        for (int i = 0; i < numGroups; i++) {
            int groupLo = lo + i * 5;
            int groupHi = Math.min(groupLo + 4, hi);
            Arrays.sort(arr, groupLo, groupHi + 1);
            medians[i] = arr[groupLo + (groupHi - groupLo) / 2];
        }
        return select(medians, 0, medians.length - 1, medians.length / 2);
    }

    private static int partitionAroundValue(int[] arr, int lo, int hi, int value) {
        int idx = -1;
        for (int i = lo; i <= hi; i++) {
            if (arr[i] == value) { idx = i; break; }
        }
        swap(arr, idx, hi);
        int pivot = arr[hi];
        int i = lo;
        for (int j = lo; j < hi; j++) {
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