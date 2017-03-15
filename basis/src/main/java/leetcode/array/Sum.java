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
        int[] a = new int[]{2, 1, -1, 2, 2, 6, 2, 8};
        System.out.println(fourSum2(a, 8));

    }

    /**
     * 给一个整数数组，找到两个数使得他们的和等于一个给定的数 target。
     * 你需要实现的函数twoSum需要返回这两个数的下标, 并且第一个下标小于第二个下标。注意这里下标的范围是 1 到 n，不是以 0 开头。
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {

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
    public static List<List<Integer>> threeSum(int[] numbers, int target) {
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
     *
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

    /**
     * 给一个包含n个数的整数数组S，在S中找到所有使得和为给定整数target的四元组(a, b, c, d)。
     *
     * @param nums
     * @param target
     * @return
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> result = new ArrayList<>();

        if (nums.length < 4) {
            return result;
        }
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                int lo = j + 1;
                int hi = nums.length - 1;

                while (lo < hi) {
                    int sum = nums[i] + nums[j] + nums[lo] + nums[hi];
                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[lo], nums[hi]));

                        do {
                            lo++;
                        }
                        while (lo < hi && nums[lo] == nums[lo - 1]);
                        do {
                            hi--;
                        }
                        while (lo < hi && nums[hi] == nums[hi + 1]);
                    } else if (sum < target) {
                        lo++;
                    } else {
                        hi--;
                    }
                }
            }
        }
        return result;
    }


    public static List<List<Integer>> fourSum2(int[] nums, int target) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        if (len < 4)
            return res;

        Arrays.sort(nums);

        int max = nums[len - 1];
        if (4 * nums[0] > target || 4 * max < target)
            return res;

        int i, z;
        for (i = 0; i < len; i++) {
            z = nums[i];
            if (i > 0 && z == nums[i - 1])// avoid duplicate
                continue;
            if (z + 3 * max < target) // z is too small
                continue;
            if (4 * z > target) // z is too large
                break;
            if (4 * z == target) { // z is the boundary
                if (i + 3 < len && nums[i + 3] == z)
                    res.add(Arrays.asList(z, z, z, z));
                break;
            }

            threeSumForFourSum(nums, target - z, i + 1, len - 1, res, z);
        }

        return res;
    }

    /*
     * Find all possible distinguished three numbers adding up to the target
     * in sorted array nums[] between indices low and high. If there are,
     * add all of them into the ArrayList fourSumList, using
     * fourSumList.add(Arrays.asList(z1, the three numbers))
     */
    public static void threeSumForFourSum(int[] nums, int target, int low, int high, ArrayList<List<Integer>> fourSumList,
                                          int z1) {
        if (low + 1 >= high)
            return;

        int max = nums[high];
        if (3 * nums[low] > target || 3 * max < target)
            return;

        int i, z;
        for (i = low; i < high - 1; i++) {
            z = nums[i];
            if (i > low && z == nums[i - 1]) // avoid duplicate
                continue;
            if (z + 2 * max < target) // z is too small
                continue;

            if (3 * z > target) // z is too large
                break;

            if (3 * z == target) { // z is the boundary
                if (i + 1 < high && nums[i + 2] == z)
                    fourSumList.add(Arrays.asList(z1, z, z, z));
                break;
            }

            twoSumForFourSum(nums, target - z, i + 1, high, fourSumList, z1, z);
        }

    }

    /*
     * Find all possible distinguished two numbers adding up to the target
     * in sorted array nums[] between indices low and high. If there are,
     * add all of them into the ArrayList fourSumList, using
     * fourSumList.add(Arrays.asList(z1, z2, the two numbers))
     */
    public static void twoSumForFourSum(int[] nums, int target, int low, int high, ArrayList<List<Integer>> fourSumList,
                                        int z1, int z2) {

        if (low >= high)
            return;

        if (2 * nums[low] > target || 2 * nums[high] < target)
            return;

        int i = low, j = high, sum, x;
        while (i < j) {
            sum = nums[i] + nums[j];
            if (sum == target) {
                fourSumList.add(Arrays.asList(z1, z2, nums[i], nums[j]));

                x = nums[i];
                while (++i < j && x == nums[i]) // avoid duplicate
                    ;
                x = nums[j];
                while (i < --j && x == nums[j]) // avoid duplicate
                    ;
            }
            if (sum < target)
                i++;
            if (sum > target)
                j--;
        }
        return;
    }
}
