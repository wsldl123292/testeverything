package algs4.bsq;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/6/12 14:57
 */
public class DoublyLinkedList<Item> implements Iterable {

    private int N;
    private Node pre;
    private Node post;

    @Override
    public Iterator iterator() {
        return null;
    }

    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void add(Item item) {
        Node last = post.prev;
        Node x = new Node();
        x.item = item;
        x.next = post;
        x.prev = last;
        post.prev = x;
        last.next = x;
        N++;
    }

    private class DoublyLinkedListIterator implements Iterator<Item> {
        private Node current = pre.next;  // the node that is returned by next()
        private Node lastAccessed = null;      // the last node to be returned by prev() or next()
        // reset to null upon intervening remove() or add()
        private int index = 0;

        public boolean hasPrevious() {
            return index > 0;
        }

        public int previousIndex() {
            return index - 1;
        }

        public int nextIndex() {
            return index;
        }

        @Override
        public boolean hasNext() {
            return index < N;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastAccessed = current;
            Item item = current.item;
            current = current.next;
            index++;
            return item;
        }

        @Override
        public void remove() {
            if (lastAccessed == null) {
                throw new IllegalStateException();
            }
            Node x = lastAccessed.prev;
            Node y = lastAccessed.next;
            x.next = y;
            y.prev = x;
            N--;
            if (current == lastAccessed)
                current = y;
            else
                index--;
            lastAccessed = null;
        }

        public Item previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            current = current.prev;
            index--;
            lastAccessed = current;
            return current.item;
        }

        public void set(Item item) {
            if (lastAccessed == null) {
                throw new IllegalStateException();
            }
            lastAccessed.item = item;
        }

        // add element to list
        public void add(Item item) {
            Node x = current.prev;
            Node y = new Node();
            Node z = current;
            y.item = item;
            x.next = y;
            y.next = z;
            z.prev = y;
            y.prev = x;
            N++;
            index++;
            lastAccessed = null;
        }
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Object item : this)
            s.append(item + " ");
        return s.toString();
    }

    public static void main(String[] args) {

    }
}
