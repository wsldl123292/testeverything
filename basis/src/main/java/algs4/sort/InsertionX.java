package algs4.sort;

import algs4.StdOut;

import java.util.Comparator;

/**
 * 作者: LDL
 * 说明:
 * 时间: 2015/9/5 15:21
 */
public class InsertionX {

    // This class should not be instantiated.
    private InsertionX() {
    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     *
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a) {

        int N = a.length;
        int exchanges = 0;
        for (int i = N - 1; i > 0; i--) {
            if (less(a[i], a[i - 1])) {
                exch(a, i, i - 1);
                exchanges++;
            }
        }

        if (exchanges == 0) return;


        // insertion sort with half-exchanges
        for (int i = 2; i < N; i++) {
            Comparable v = a[i];
            int j = i;
            while (less(v, a[j - 1])) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = v;
        }

        assert isSorted(a);
    }


    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // is v < w ?
    private static boolean less(Comparator c, Object v, Object w) {
        return c.compare(v, w) < 0;
    }


    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }


    /***************************************************************************
     * Check if array is sorted - useful for debugging.
     ***************************************************************************/

    // is the array a[] sorted?
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    // is the array sorted from a[lo] to a[hi]
    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    // is the array a[] sorted?
    private static boolean isSorted(Object[] a, Comparator c) {
        return isSorted(a, c, 0, a.length - 1);
    }

    // is the array sorted from a[lo] to a[hi]
    private static boolean isSorted(Object[] a, Comparator c, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(c, a[i], a[i - 1])) return false;
        return true;
    }


    // print array to standard output
    private static void show(Comparable[] a) {
        for (Comparable anA : a) {
            StdOut.println(anA);
        }
    }

    /**
     * Reads in a sequence of strings from standard input; selection sorts them;
     * and prints them to standard output in ascending order.
     */
    public static void main(String[] args) {
        Integer[] a = {2, 56, 3, 2, 66, 4};
        InsertionX.sort(a);
        show(a);
    }
}
