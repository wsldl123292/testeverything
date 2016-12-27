package algs4.bsq;

/**
 * 作者: LDL
 * 功能说明: 链式队列
 * 创建日期: 2015/6/10 18:26
 */
public class LinkedQueue<Item> {

    private Node first;
    private Node last;
    private int N;

    private class Node {
        Item item;
        Node next;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void enqueue(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        N++;
    }

    public Item dequeue() {
        Item item = first.item;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        N--;
        return item;
    }
}
