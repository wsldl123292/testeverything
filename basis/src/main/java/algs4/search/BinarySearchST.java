package algs4.search;


import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2017-01-09 22:32
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {

    private static final int INIT_CAPACITY = 2;

    private Key[] keys;

    private Value[] vals;

    private int n = 0;

    public BinarySearchST() {
        this(INIT_CAPACITY);
    }


    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size() {
        return n;
    }

    public boolean isEmpty(){
        return size() == 0;
    }
    public boolean contains(Key key) {
        checkNotNull(key, "argument to contains() is null");
        return get(key) != null;
    }

    public Value get(Key key){
        checkNotNull(key, "argument to get() is null");
        if(isEmpty()){
            return null;
        }
        return null;
    }
}
