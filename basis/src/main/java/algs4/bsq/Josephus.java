package algs4.bsq;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 作者: LDL
 * 功能说明: 约瑟夫环
 * 创建日期: 2015/6/12 14:06
 */
public class Josephus {

    public static void main(String[] args) {
        int n = 9;
        int m = 4;
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 1; i < n + 1; i++) {
            queue.add(i);
        }

        while (!queue.isEmpty()) {
            for (int i = 0; i < m - 1; i++) {
                queue.add(queue.poll());
            }
            System.out.println(queue.poll());
        }
    }
}
