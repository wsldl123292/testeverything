package threadsyncutilities.cyclicbarrier;

import java.util.Random;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-02 20:14
 */
public class MatrixMock {

    private int[][] data;

    public MatrixMock(int size, int length, int number) {
        int countter = 0;
        this.data = new int[size][length];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < length; j++) {
                data[i][j] = random.nextInt(10);
                if (data[i][j] == number) {
                    countter++;
                }
            }
        }

        System.out.printf("Mock: There are %d ocurrences of number in generated data.\n", countter, number);
    }


    public int[] getRow(int row) {
        if (row > 0 && row < data.length) {
            return data[row];
        }
        return null;
    }

}
