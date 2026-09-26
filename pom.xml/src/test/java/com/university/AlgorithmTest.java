package com.university;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class AlgorithmTest {

    private static final Random RANDOM = new Random();

    // ---------- MergeSort ----------

    @Test
    void mergeSortRandom() {
        int[] arr = randomArray(1000);
        checkSort(arr, MergeSorter::sort);
    }

    @Test
    void mergeSortSorted() {
        int[] arr = sortedArray(500);
        checkSort(arr, MergeSorter::sort);
    }

    @Test
    void mergeSortReverse() {
        int[] arr = reverseArray(500);
        checkSort(arr, MergeSorter::sort);
    }

    @Test
    void mergeSortDuplicates() {
        int[] arr = duplicateArray(500);
        checkSort(arr, MergeSorter::sort);
    }

    @Test
    void mergeSortEmpty() {
        int[] arr = new int[0];
        MergeSorter.sort(arr);
        assertEquals(0, arr.length);
    }

    @Test
    void mergeSortSingleElement() {
        int[] arr = {42};
        MergeSorter.sort(arr);
        assertArrayEquals(new int[]{42}, arr);
    }

    // ---------- QuickSort ----------

    @Test
    void quickSortRandom() {
        int[] arr = randomArray(1000);
        checkSort(arr, QuickSorter::sort);
    }

    @Test
    void quickSortSorted() {
        int[] arr = sortedArray(500);
        checkSort(arr, QuickSorter::sort);
    }

    @Test
    void quickSortReverse() {
        int[] arr = reverseArray(500);
        checkSort(arr, QuickSorter::sort);
    }

    @Test
    void quickSortDuplicates() {
        int[] arr = duplicateArray(500);
        checkSort(arr, QuickSorter::sort);
    }

    @Test
    void quickSortEmpty() {
        int[] arr = new int[0];
        QuickSorter.sort(arr);
        assertEquals(0, arr.length);
    }

    @Test
    void quickSortSingleElement() {
        int[] arr = {42};
        QuickSorter.sort(arr);
        assertArrayEquals(new int[]{42}, arr);
    }

    // ---------- DeterministicSelector ----------

    @Test
    void selectMatchesSortedArray() {
        for (int trial = 0; trial < 100; trial++) {
            int n = 10 + RANDOM.nextInt(200);
            int[] arr = randomArray(n);
            int k = RANDOM.nextInt(n);

            int[] copy = arr.clone();
            int expected = Arrays.stream(copy).sorted().toArray()[k];

            int actual = DeterministicSelector.select(arr.clone(), k);
            assertEquals(expected, actual, "Failed on trial " + trial + " n=" + n + " k=" + k);
        }
    }

    // ---------- ClosestPairSolver ----------

    @Test
    void closestPairMatchesBruteForce() {
        for (int trial = 0; trial < 20; trial++) {
            int n = 4 + RANDOM.nextInt(200);
            Point[] points = randomPoints(n);

            double expected = bruteForce(points);
            double actual = ClosestPairSolver.closestPair(points);

            assertEquals(expected, actual, 1e-9, "Failed on trial " + trial + " n=" + n);
        }
    }

    // ---------- Helpers ----------

    private interface Sorter {
        void sort(int[] arr);
    }

    private void checkSort(int[] arr, Sorter sorter) {
        int[] expected = arr.clone();
        Arrays.sort(expected);
        sorter.sort(arr);
        assertArrayEquals(expected, arr);
    }

    private int[] randomArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = RANDOM.nextInt(10000);
        return arr;
    }

    private int[] sortedArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i;
        return arr;
    }

    private int[] reverseArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = n - i;
        return arr;
    }

    private int[] duplicateArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = RANDOM.nextInt(5);
        return arr;
    }

    private Point[] randomPoints(int n) {
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point(RANDOM.nextDouble() * 1000, RANDOM.nextDouble() * 1000);
        }
        return points;
    }

    private double bruteForce(Point[] points) {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < points.length; i++)
            for (int j = i + 1; j < points.length; j++)
                min = Math.min(min, points[i].distanceTo(points[j]));
        return min;
    }
}