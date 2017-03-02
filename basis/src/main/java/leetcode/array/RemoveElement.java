package leetcode.array;

import java.util.Random;

/**
 * @描述 在一个数组里面移除指定value， 并且返回新的数组长度。
 * 这题唯一需要注意的地方在于 in place ， 不能新建另一个数组
 * @作者 liudelin
 * @日期 2017/2/28 19:56
 */
public class RemoveElement {

    public static void main(String[] args) {
        /*Element[] array = generateElementArray(100);
        System.out.println(array.length);
        System.out.println(remove(array, 6));*/

        int[] array = new int[]{1, 1, 1, 1, 1, 1, 1, 5};
        System.out.println(removeDuplicatesFromSortedArray(array));
    }


    private static int remove(Element[] array, int target) {

        int j = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i].getA() != target) {
                array[j] = array[i];
                j++;
            }

        }
        return j;
    }

    //排好序的数组删除重复元素,同一元素最多保留2个
    private static int removeDuplicatesFromSortedArray2(int[] array) {
        int j = 0;
        int count = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] != array[j]) {

                array[++j] = array[i];
                count = 0;
            } else {
                count++;
                if(count < 2){
                    array[++j] = array[i];
                }
            }
        }
        return j + 1;
    }

    //排好序的数组删除重复元素
    private static int removeDuplicatesFromSortedArray(int[] array) {
        int j = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] != array[j]) {

                array[++j] = array[i];
            }
        }
        return j + 1;
    }

    public static Element[] generateElementArray(int size) {
        Element[] array = new Element[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            Element element = new Element();
            element.setA(random.nextInt(10));
            array[i] = element;
        }

        return array;
    }

    public static int[] generateArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(10);
        }

        return array;
    }


    public static class Element {
        private int a;

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }
    }
}
