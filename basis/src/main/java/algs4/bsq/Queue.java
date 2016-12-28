package algs4.bsq;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-12-28 22:46
 */
public class Queue<Item> implements Iterator<Item> {
    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        return false;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public Item next() {
        return null;
    }

    private Node<Item> first;
    private Node<Item> last;
    private int size;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    public Queue() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return first.item;
    }
}
