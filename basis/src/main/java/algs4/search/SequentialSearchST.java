package algs4.search;

import algs4.StdIn;
import algs4.StdOut;
import algs4.bsq.Queue;

import java.util.Iterator;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 功能: 符号表
 * 作者: ldl
 * 时间: 2016-12-28 21:53
 */
public class SequentialSearchST<Key, Value> {

    private Node first;
    private int size;

    private class Node {
        Key key;
        Value value;

        Node next;

        Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }


    public Value get(Key key) {
        checkNotNull(key, "argument to get() is null");
        for (Node x = first; x != null; x = x.next) {
            if (x.key.equals(key)) {
                return x.value;
            }
        }

        return null;
    }

    public void put(Key key, Value value) {
        checkNotNull(key, "argument to put() is null");
        if (value == null) {
            delete(key);
            return;
        }
        for (Node x = first; x != null; x = x.next) {
            if (x.key.equals(key)) {
                x.value = value;
                return;
            }
        }

        first = new Node(key, value, first);
        size++;
    }

    public void delete(Key key) {
        checkNotNull(key, "argument to delete() is null");
        first = delete(first, key);
    }

    private Node delete(Node first, Key key) {
        if (first == null) return null;
        if (key.equals(first.key)) {
            size--;
            return first.next;
        }
        first.next = delete(first.next, key);
        return first;
    }


    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Key key) {
        checkNotNull(key, "argument to contains() is null");
        return get(key) != null;
    }


    public Iterator<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (Node x = first; x != null; x = x.next)
            queue.enqueue(x.key);
            return queue;
    }

    public static void main(String[] args) {
        SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (Iterator<String> it = st.keys(); it.hasNext(); ) {
            String s = it.next();
            StdOut.println(s + " " + st.get(s));
        }
    }
}
