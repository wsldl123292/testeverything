package forkjoin.cancle;

import java.util.Random;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-05 22:29
 */
public class ArrayGenerator {

    public int[] generateArray(int size){
        int array[] = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(10);
        }

        return array;
    }


}
