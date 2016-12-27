package lists;

import java.util.*;

/**
 * 功能描述:
 * 作者: LDL
 * 创建时间: 2015/3/22 15:36
 */
public class LinkListReversion {

    public static void main(String[] args){
        Queue<String> queue = new LinkedList<>();
        queue.add("h");
        queue.add("e");
        queue.add("l");
        queue.add("l");
        queue.add("o");
        queue.offer("a");
        queue.clear();
        System.out.println(queue);


        Stack<String> stack = new Stack<>();
        stack.push("h");
        stack.push("e");
        stack.push("l");
        stack.push("l");
        stack.push("o");
        System.out.println(stack);

        List<String> linkedList = new LinkedList<>();
        linkedList.add("h");
        linkedList.add("e");
        linkedList.add("l");
        linkedList.add("l");
        linkedList.add("o");

        ListIterator<String> aIter = linkedList.listIterator();
        while (aIter.hasNext()){
            System.out.println(aIter.nextIndex()+":"+aIter.next());
        }
    }
}
