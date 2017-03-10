package leetcode.array;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.Collection;

/**
 * @描述
 * @作者 liudelin
 * @日期 2017/3/9 14:02
 */
public class Sum {

    public static void main(String[] args) {
        int[] a = new int[]{2, 7, 11, 15, 7, 3, 6, 3, 3, 3, 2, 0};
        System.out.println(sum3(a, 9));
    }

    public static String sum2(int[] a, int target) {

        Multimap<Integer, Integer> myMultimap = ArrayListMultimap.create();
        for (int i = 0; i < a.length; i++) {
            myMultimap.put(a[i], i + 1);
        }

        int ret;
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer integer : myMultimap.keySet()) {
            ret = target - integer;
            if (myMultimap.containsKey(ret)) {
                Collection<Integer> collection = myMultimap.get(ret);
                Collection<Integer> collection1 = myMultimap.get(integer);
                for (Integer integer1 : collection) {
                    for (Integer integer2 : collection1) {
                        if (integer1 < integer2) {
                            stringBuilder.append("index:").append(integer1).append(",index:").append(integer2).append("\n");
                        }
                    }
                }

            }
        }
        return stringBuilder.toString();
    }

    public static String sum3(int[] a, int target) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            int j = i + 1;
            while (j < a.length) {
                int ret = a[i] + a[j];
                if (ret <= target) {
                    int k = a.length - 1;
                    while (k > j) {
                        if (ret + a[k] == target) {
                            stringBuilder.append(i + 1).append(",").append(j + 1).append(",").append(k + 1).append("\n");
                        }
                        k--;
                    }
                }
                j++;
            }
        }
        return stringBuilder.toString();
    }
}
