package threadsyncutilities.cyclicbarrier;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-02 20:20
 */
public class Result {

    private int data[];

    public Result(int size) {

        this.data = new int[size];
    }

    public void setData(int position, int value) {
        data[position] = value;
    }

    public int[] getData() {
        return data;
    }
}
