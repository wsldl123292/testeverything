import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/8/25 16:39
 */
public class MyTree<T> {
    private final int DEFAULT_SIZE = 2;
    private int size;
    private int count;
    private Object[] nodes;

    public MyTree() {
        this.size = this.DEFAULT_SIZE;
        this.nodes = new Object[this.size];
        this.count = 0;
    }

    public MyTree(Node<T> root) {
        this();
        this.count = 1;
        this.nodes[0] = root;
    }

    public MyTree(Node<T> root, int size) {
        this.size = size;
        this.nodes = new Object[this.size];
        this.count = 1;
        this.nodes[0] = root;
    }

    //添加一个节点
    public void add(Node<T> node) {
        for (int i = 0; i < this.size; i++) {
            if (this.nodes[i] == null) {
                nodes[i] = node;
                break;
            }
        }
        this.count++;
    }

    public void check() {
        if (this.count >= this.size) {
            this.enlarge();
        }
    }

    //添加一个节点，并指明父节点
    public void add(Node<T> node, Node<T> parent) {
        this.check();
        node.setParent(this.position(parent));
        this.add(node);
    }

    //获取节点在数组的存储位置
    public int position(Node<T> node) {
        for (int i = 0; i < this.size; i++) {
            if (nodes[i] == node) {
                return i;
            }
        }
        return -1;
    }

    //获取整棵树有多少节点
    public int getSize() {
        return this.count;
    }

    //获取根节点
    @SuppressWarnings("unchecked")
    public Node<T> getRoot() {
        return (Node<T>) this.nodes[0];
    }

    //获取所以节点，以List形式返回
    @SuppressWarnings("unchecked")
    public List<Node<T>> getAllNodes() {
        List<Node<T>> list = new ArrayList<Node<T>>();
        for (int i = 0; i < this.size; i++) {
            if (this.nodes[i] != null) {
                list.add((Node<T>) nodes[i]);
            }
        }
        return list;
    }

    //获取树的深度，只有根节点时为1
    @SuppressWarnings("unchecked")
    public int getDepth() {

        int max = 1;
        if (this.nodes[0] == null) {
            return 0;
        }

        for (int i = 0; i < this.count; i++) {
            int deep = 1;
            int location = ((Node<T>) (this.nodes[i])).getParent();
            while (location != -1 && this.nodes[location] != null) {
                location = ((Node<T>) (this.nodes[location])).getParent();
                deep++;
            }
            if (max < deep) {
                max = deep;
            }
        }
        return max;
    }

    public void enlarge() {
        this.size = this.size + this.DEFAULT_SIZE;
        Object[] newNodes = new Object[this.size];
        newNodes = Arrays.copyOf(nodes, this.size);
        Arrays.fill(nodes, null);
        this.nodes = newNodes;
    }
}
