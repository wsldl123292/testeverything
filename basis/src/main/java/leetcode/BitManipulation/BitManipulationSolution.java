package leetcode.BitManipulation;

/**
 * @描述
 * @作者 liudelin
 * @日期 2017/3/29 17:32
 */
public class BitManipulationSolution {

    public static int missingNumber(int[] nums) {
        int xor = 0, i;
        for (i = 0; i < nums.length; i++) {
            xor = xor ^ i ^ nums[i];
        }

        return xor ^ i;
    }

    public static void main(String[] args) {
        int[] a = new int[]{0, 1, 2, 4, 5};
        System.out.println(missingNumber(a));
    }
}
