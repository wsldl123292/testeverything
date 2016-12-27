package algs4.strings;

import java.util.Arrays;

/**
 * 说明 键索引计数法
 * 作者 LDL
 * 时间 2016/5/22.
 */
public class KeyIndex {

    public static void main(String[] args) {


        int[] src = new int[]{2, 2, 4, 0, 1, 1, 1, 2, 1};
        int R = 5;
        int N = src.length;
        int[] aux = new int[N];
        int[] count = new int[R + 1];


        //计算出现频率
        for (int aSrc : src) {
            count[aSrc + 1]++;
        }

        //建立索引
        for (int i = 0; i < R; i++) {
            count[i + 1] += count[i];
        }

        for (int aSrc : src) {
            aux[count[aSrc]++] = aSrc;
        }

        System.out.println(Arrays.toString(aux));


    }
}