package com.university;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPairSolver {

    public static double closestPair(Point[] points) {
        Point[] byX = points.clone();
        Arrays.sort(byX, Comparator.comparingDouble(p -> p.x));
        return closest(byX, 0, byX.length - 1);
    }

    private static double closest(Point[] byX, int lo, int hi) {
        if (hi - lo <= 3) {
            return bruteForce(byX, lo, hi);
        }
        int mid = lo + (hi - lo) / 2;
        double midX = byX[mid].x;

        double dLeft = closest(byX, lo, mid);
        double dRight = closest(byX, mid + 1, hi);
        double d = Math.min(dLeft, dRight);

        final double dForFilter = d;
        Point[] strip = Arrays.stream(byX, lo, hi + 1)
                .filter(p -> Math.abs(p.x - midX) < dForFilter)
                .sorted(Comparator.comparingDouble(p -> p.y))
                .toArray(Point[]::new);

        for (int i = 0; i < strip.length; i++) {
            for (int j = i + 1; j < strip.length && (strip[j].y - strip[i].y) < d; j++) {
                d = Math.min(d, strip[i].distanceTo(strip[j]));
            }
        }
        return d;
    }

    private static double bruteForce(Point[] pts, int lo, int hi) {
        double min = Double.MAX_VALUE;
        for (int i = lo; i <= hi; i++)
            for (int j = i + 1; j <= hi; j++)
                min = Math.min(min, pts[i].distanceTo(pts[j]));
        return min;
    }
}