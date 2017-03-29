package leetcode.array;

import java.util.Arrays;

/**
 * @描述
 * @作者 liudelin
 * @日期 2017/3/15 18:28
 */
public class ArraySolution {

    /**
     * 假设一个旋转排序的数组其起始位置是未知的（比如0 1 2 4 5 6 7 可能变成是4 5 6 7 0 1 2）。
     * 你需要找到其中最小的元素。
     * 你可以假设数组中不存在重复的元素。
     */


    public static int findMin(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return min(nums[0], nums[1]);
        }
        if (nums[0] < nums[nums.length - 1]) {
            return nums[0];
        }

        if (nums[0] >= nums[nums.length - 1]) {
            int i = nums.length - 1;
            while ((i - 1) >= 0) {
                if (nums[i - 1] > nums[i]) {
                    return nums[i];
                }
                i--;
            }
        }
        return nums[0];
    }


    public static int findMinBinarySearch(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return min(nums[0], nums[1]);
        }

        int start = 0;
        int end = nums.length - 1;

        while (start < end) {
            if (nums[start] < nums[end])
                return nums[start];

            int mid = (start + end) / 2;

            if (nums[mid] >= nums[start]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return nums[start];
    }


    private static int min(int a, int b) {
        if (a > b) {
            return b;
        }

        return a;
    }


    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        } else if (x == 0) {
            return true;
        }

        int p = x;
        int q = 0;

        while (p >= 10) {
            q *= 10;
            q += p % 10;
            p /= 10;
        }

        return q == x / 10 && p == x % 10;


    }


    public boolean searchMatrix(int[][] matrix, int target) {

        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int m = matrix.length, n = matrix[0].length;
        int start = 0, end = m * n - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int e = matrix[mid / n][mid % n];
            if (target < e) {
                end = mid - 1;
            } else if (target > e) {
                start = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }


    public static int[] searchRange(int[] nums, int target) {

        int[] result = new int[]{-1, -1};
        if (nums.length == 0) {
            return result;
        }

        int index = Arrays.binarySearch(nums, target);
        if (index < 0) {
            return result;
        } else {
            int k = index;
            int m = index;
            while (k >= 0) {
                if (nums[k] == target) {
                    result[0] = k;
                    k--;
                } else {
                    break;
                }
            }
            while (m <= nums.length - 1) {
                if (nums[m] == target) {
                    result[1] = m;
                    m++;
                } else {
                    break;
                }
            }
        }

        return result;
    }


    public static int searchInsert(int[] nums, int target) {

        /*int hi = nums.length - 1;
        int lo = 0;
        while (hi >= lo) {
            int mid = (lo + hi) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return lo;*/
        /*int index = Arrays.binarySearch(nums, target);
        if (index < 0) {
            if (target > nums[nums.length - 1]) {
                return nums.length;
            }
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > target) {
                    return i;
                }
            }
        }

        return index;*/

        int i = 0;
        while (nums[i] < target) {
            i++;
            if (i == nums.length) {
                break;
            }
        }
        return i;
    }


    public int findPeakElement(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        int start = 0;
        int end = n - 1;
        int mid = 0;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if ((mid == 0 || nums[mid] >= nums[mid - 1]) &&
                    (mid == n - 1 || nums[mid] >= nums[mid + 1])) {
                return mid;
            } else if (mid > 0 && nums[mid - 1] > nums[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return mid;
    }


    public static void main(String[] args) {
        int[] a = new int[]{1};
        System.out.println(searchInsert(a, 2));
    }
}
