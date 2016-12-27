package algs4.bsq;

import java.util.Iterator;

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/6/10 18:38
 */
public class Bag<Item> implements Iterable<Item> {
    private Node first;
    private int N;

    public void add(Item item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }

    public int size(){
        return N;
    }

    public boolean isEmpty(){
        return first == null;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    /**
     * 节点
     */
    private class Node{
        Item item;
        Node next;
    }

    private class ListIterator implements Iterator<Item>{

        private Node current = first;
        @Override
        public boolean hasNext() {
            return current!=null;
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
}
