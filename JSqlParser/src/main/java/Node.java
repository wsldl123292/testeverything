/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/8/25 16:39
 */
public class Node<T> {
    private T data;
    private int parent;

    public Node() {
    }

    public Node(T data) {
        this.data = data;
    }

    public Node(T data, int parent) {
        this.data = data;
        this.parent = parent;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return this.data;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getParent() {
        return this.parent;
    }
}
