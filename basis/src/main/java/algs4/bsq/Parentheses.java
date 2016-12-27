package algs4.bsq;

/**
 * 作者: LDL
 * 说明: 判断符号完整
 * 时间: 2015/6/10 21:44
 */
public class Parentheses {

    public static void main(String[] args) {
        String testStr = "[(])";
        LinkedStack<Character> stack = new LinkedStack<>();
        for (char c : testStr.toCharArray()) {
            if (!stack.isEmpty()) {
                char f = stack.peek();
                if (getChar(c) == f) {
                    stack.pop();
                    continue;
                }
            }
            stack.push(c);

        }

        System.out.println(stack.isEmpty());
    }


    public static char getChar(char c) {
        char r = 0;
        switch (c) {
            case '(':
                r = ')';
                break;
            case '[':
                r = ']';
                break;
            case '{':
                r = '}';
                break;
            case ')':
                r = '(';
                break;
            case ']':
                r = '[';
                break;
            case '}':
                r = '{';
                break;
        }

        return r;
    }
}
