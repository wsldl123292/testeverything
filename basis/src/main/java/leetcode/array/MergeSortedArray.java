package leetcode.array;

import java.util.Arrays;

/**
 * @描述
 * @作者 liudelin
 * @日期 2017/3/2 18:48
 */
public class MergeSortedArray {

    public static void main(String[] args) {
        int[] a = new int[10];
        a[0] = 1;
        a[1] = 3;
        a[2] = 5;
        a[3] = 7;
        a[4] = 9;
        int[] b = new int[]{2, 4, 6, 8, 10};

        System.out.println(Arrays.toString(merge(a, 5, b, 5)));
    }

    public static int[] merge(int[] a, int m, int[] b, int n) {

        int i = m + n - 1;
        int j = m - 1;
        int k = n - 1;
        while (i >= 0) {
            if (j >= 0 && k >= 0) {
                if (a[j] > b[k]) {
                    a[i] = a[j];
                    j--;
                } else {
                    a[i] = b[k];
                    k--;
                }
            } else if (j >= 0) {
                a[i] = a[j];
                j--;
            } else if (k >= 0) {
                a[i] = b[k];
                k--;
            }

            i--;
        }

        return a;
    }

}
