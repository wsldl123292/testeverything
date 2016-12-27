package algs4.strings;

import java.util.Arrays;

/**
 * 说明 低位优先排序
 * 作者 LDL
 * 时间 2016/6/1.
 */
public class LSD {
    private static final int BITS_PER_BYTE = 8;

    private static void sort(String[] s, int W) {
        int N = s.length;
        int R = 256;
        String[] aux = new String[N];
        for (int i = W - 1; i >= 0; i--) {
            int[] count = new int[R + 1];

            for (String value : s) {
                count[value.charAt(i) + 1]++;
            }

            for (int j = 0; j < R; j++) {
                count[j + 1] += count[j];
            }

            for (String value : s) {
                aux[count[value.charAt(i)]++] = value;
            }

            System.arraycopy(aux, 0, s, 0, N);
        }
    }


    public static void sort(int[] a) {
        int BITS = 32;                 // each int is 32 bits
        int W = BITS / BITS_PER_BYTE;  // each int is 4 bytes
        int R = 1 << BITS_PER_BYTE;    // each bytes is between 0 and 255
        int MASK = R - 1;              // 0xFF

        int N = a.length;
        int[] aux = new int[N];

        for (int d = 0; d < W; d++) {

            // compute frequency counts
            int[] count = new int[R+1];
            for (int anA : a) {
                int c = (anA >> BITS_PER_BYTE * d) & MASK;
                count[c + 1]++;
            }

            // compute cumulates
            for (int r = 0; r < R; r++)
                count[r+1] += count[r];

            // for most significant byte, 0x80-0xFF comes before 0x00-0x7F
            if (d == W-1) {
                int shift1 = count[R] - count[R/2];
                int shift2 = count[R/2];
                for (int r = 0; r < R/2; r++)
                    count[r] += shift1;
                for (int r = R/2; r < R; r++)
                    count[r] -= shift2;
            }

            // move data
            for (int anA : a) {
                int c = (anA >> BITS_PER_BYTE * d) & MASK;
                aux[count[c]++] = anA;
            }

            // copy back
            System.arraycopy(aux, 0, a, 0, N);
        }
    }


    public static void main(String[] args) {

        String[] a = new String[]{"4PGC938","2IYE230","3CIO730","1ICK750","1OHV845","2JYE230"};

        sort(a,7);

        System.out.println(Arrays.toString(a));
    }
}
