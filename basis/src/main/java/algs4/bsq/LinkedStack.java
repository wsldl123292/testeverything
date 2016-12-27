package algs4.bsq;

import java.util.Iterator;

import static org.mockito.internal.util.Checks.checkNotNull;

/**
 * 作者: LDL
 * 功能说明: 链栈
 * 创建日期: 2015/6/10 18:08
 */
public class LinkedStack<Item> implements Iterable<Item> {

    //栈顶元素
    private Node first;
    private int N;

    public void push(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    public Item peek() {
        checkNotNull(first, "链表为空");
        Item item = first.item;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {

        }
    }

    /**
     * 节点
     */
    private class Node {
        Item item;
        Node next;
    }
}
