package algs4.bsq;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/6/12 11:26
 */
public class ResizingArrayQueue<Item> {

    private Item[] q;
    private int N;
    private int first;
    private int last;

    public ResizingArrayQueue(int cap) {
        q = (Item[]) new Object[cap];
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void enQueue(Item item) {
        q[last++] = item;
        if (last == q.length) {
            last = 0;
        }
        N++;
    }

    private void resize(int max) {
        assert max >= N;
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = q[(first + i) % q.length];
        }
        q = temp;
        first = 0;
        last = N;
    }

    public Item deQueue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        Item item = q[first];
        q[first] = null;
        N--;
        first++;
        if (first == q.length) {
            first = 0;
        }
        if (N > 0 && N == q.length / 4) {
            resize(q.length / 2);
        }
        return item;
    }

    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        return q[first];
    }


    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;

        public boolean hasNext() {
            return i < N;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = q[(i + first) % q.length];
            i++;
            return item;
        }
    }
}
