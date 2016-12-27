package algs4.bsq;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.h2.mvstore.DataUtils.checkArgument;

/**
 * 作者: LDL
 * 说明: 可变大小的数组栈
 * 时间: 2015/6/7 17:37
 */
public class FixedCapacityStack<Item> implements Iterable<Item> {

    private Item[] a;

    public FixedCapacityStack(int cap) {
        a = (Item[]) new Object[cap];
    }

    private int N;

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(Item item) {
        if (N == a.length) {
            resize(2 * a.length);
        }
        a[N++] = item;
    }

    public Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        Item item = a[--N];
        a[N] = null;
        if (N > 0 && N == a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }

    private void resize(int capacity) {
        checkArgument(capacity > N, "argument error");
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }


    private class ReverseArrayIterator implements Iterator<Item> {

        private int i;

        public ReverseArrayIterator() {
            i = N - 1;
        }

        public boolean hasNext() {
            return i >= 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return a[i--];
        }
    }

    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        return a[N - 1];
    }
}
