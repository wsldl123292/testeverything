package leetcode.array;

import java.util.*;

/**
 * @描述
 * @作者 liudelin
 * @日期 2017/3/9 14:02
 */
public class Sum {

    public static void main(String[] args) {
        int length = 10000;

        /*int[] a = new int[length];
        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * 100);
        }*/
        int[] a = new int[]{-2, -1, 3, 2, 2, 7, 11, 15};
        System.out.println(threeSumClosest(a, 50));

    }

    /**
     * 给一个整数数组，找到两个数使得他们的和等于一个给定的数 target。
     * 你需要实现的函数twoSum需要返回这两个数的下标, 并且第一个下标小于第二个下标。注意这里下标的范围是 1 到 n，不是以 0 开头。
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] sum2(int[] nums, int target) {

        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int ret = target - nums[i];
            if (map.containsKey(ret)) {
                int index = map.get(ret);
                if (index != i) {
                    result[0] = i;
                    result[1] = index;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 给出一个有n个整数的数组S，在S中找到三个整数a, b, c，找到所有使得a + b + c = target的三元组。
     * 注意事项
     * <p>
     * 在三元组(a, b, c)，要求a <= b <= c。
     * <p>
     * 结果不能包含重复的三元组。
     *
     * @param numbers 初始数组
     * @param target  结果
     * @return
     */
    public static List<List<Integer>> sum3(int[] numbers, int target) {
        List<List<Integer>> result = new ArrayList<>();

        if (numbers.length < 2) {
            return result;
        }
        //排序
        Arrays.sort(numbers);

        for (int i = 0; i < numbers.length - 2; i++) {

            int lo = i + 1;
            int hi = numbers.length - 1;
            while (lo < hi) {
                if (numbers[i] + numbers[lo] + numbers[hi] == target) {
                    result.add(Arrays.asList(numbers[i], numbers[lo], numbers[hi]));
                    lo++;
                    hi--;

                    while (lo < hi && numbers[lo] == numbers[lo - 1])
                        lo++;
                    while (lo < hi && numbers[hi] == numbers[hi + 1])
                        hi--;
                } else if (numbers[i] + numbers[lo] + numbers[hi] < target) {
                    lo++;
                } else {
                    hi--;
                }
            }

            while (i < numbers.length - 1 && numbers[i] == numbers[i + 1]) {
                i++;
            }

        }
        return result;
    }


    /**
     * 给一个包含 n 个整数的数组 S, 找到相加后的和与给定整数 target 最接近的三元组，返回这三个数的和。
     * @param nums
     * @param target
     * @return
     */
    public static int threeSumClosest(int[] nums, int target) {

        if (nums.length < 3) {
            return 0;
        }
        int result = nums[0] + nums[1] + nums[nums.length - 1];

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            int start = i + 1;
            int end = nums.length - 1;
            while (start < end) {
                int sum = nums[i] + nums[start] + nums[end];
                if (sum > target) {
                    end--;
                } else {
                    start++;
                }
                if (Math.abs(sum - target) < Math.abs(result - target)) {
                    result = sum;
                }
            }
        }
        return result;
    }
}
