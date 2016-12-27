package algs4.bsq;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 作者: LDL
 * 功能说明: 中序表达式转换为后续表达式
 * 创建日期: 2015/6/11 10:39
 */
public class InfixToPostfix {

    public static void main(String[] args) {
        String expression = "2*3/(2-1)+3*(4-1)";
        System.out.println(Calculate(fix(expression)));
    }


    public static String fix(String expression) {

        StringBuilder result = new StringBuilder();
        LinkedStack<String> numStack = new LinkedStack<String>();
        LinkedStack<Operator> operStack = new LinkedStack<Operator>();
        StringTokenizer tokenizer = new StringTokenizer(expression, "+*()/-", true);
        while (tokenizer.hasMoreElements()) {
            String to = tokenizer.nextToken();
            if (isExpression(to)) {
                numStack.push(to);
            } else {
                Operator operator = getComputeOper().get(to);
                if (operStack.isEmpty()) {
                    operStack.push(operator);
                } else {
                    Operator topOperator = operStack.peek();
                    if (operator.show().equals("(")) {
                        operStack.push(operator);
                    } else if (operator.show().equals(")")) {
                        operStack.pop();
                        numStack.push(topOperator.show());
                    } else {
                        if (topOperator.show().equals("(")) {
                            operStack.pop();
                        } else {
                            if (topOperator.priority() <= operator.priority()) {
                                operStack.pop();
                                numStack.push(topOperator.show());
                            }
                        }
                        operStack.push(operator);
                    }
                }
            }
        }
        while (!operStack.isEmpty()) {
            numStack.push(operStack.pop().show());
        }
        Iterator iterator = numStack.iterator();
        while (iterator.hasNext()) {
            result.append(iterator.next());
        }
        return result.reverse().toString();
    }


    private enum Operator {

        /**
         * 乘
         */
        MUL {
            @Override
            public int priority() {
                return 1;
            }

            @Override
            public String show() {
                return "*";
            }
        },

        /**
         * 除
         */
        DIVIDE {
            @Override
            public int priority() {
                return 1;
            }

            @Override
            public String show() {
                return "/";
            }
        },
        /**
         * 加
         */
        ADD {
            @Override
            public int priority() {
                return 2;
            }

            @Override
            public String show() {
                return "+";
            }
        },

        /**
         * 减
         */
        MINUS {
            @Override
            public int priority() {
                return 2;
            }

            @Override
            public String show() {
                return "-";
            }
        },

        /**
         * 左括号
         */
        LEFTBRACKETS {
            @Override
            public int priority() {
                return 0;
            }

            @Override
            public String show() {
                return "(";
            }
        },
        /**
         * 右括号
         */
        RIGHTBRACKETS {
            @Override
            public int priority() {
                return 0;
            }

            @Override
            public String show() {
                return ")";
            }
        };

        /**
         * 对应的优先级
         *
         * @return
         */
        public abstract int priority();

        public abstract String show();

    }

    /**
     * 判断一个字符串是否是非操作符
     *
     * @param str
     * @return
     */
    private static boolean isExpression(String str) {
        return !(str.contains("(") || str.contains(")") || str.contains("+") || str.contains("*")
                || str.contains("-") || str.contains("/"));
    }

    /**
     * 获取运算操作符
     *
     * @return
     */
    private static Map<String, Operator> getComputeOper() {
        return new HashMap<String, Operator>() { // 运算符
            private static final long serialVersionUID = 7706718608122369958L;

            {
                put("+", Operator.ADD);
                put("-", Operator.MINUS);
                put("*", Operator.MUL);
                put("/", Operator.DIVIDE);
                put("(", Operator.LEFTBRACKETS);
                put(")", Operator.RIGHTBRACKETS);
            }
        };
    }


    private static int Calculate(String expression) {
        LinkedStack<Integer> resultStack = new LinkedStack<>();
        for (int i = 0; i < expression.length(); i++) {
            String to = expression.substring(i, i + 1);
            if (isExpression(to)) {
                resultStack.push(Integer.parseInt(to));
                /*if{
                    if(operStack.isEmpty()){
                        resultStack.push(Integer.parseInt(to));
                    }else {
                        resultStack.push(getResult(Integer.parseInt(to),resultStack.pop(),operStack.pop()));
                    }
                }*/
            } else {
                resultStack.push(getResult(resultStack.pop(), resultStack.pop(), to));
            }
        }
        return resultStack.pop();
    }


    private static int getResult(int a, int b, String type) {
        if (type.equals("+")) {
            return b + a;
        }
        if (type.equals("-")) {
            return b - a;
        }
        if (type.equals("*")) {
            return b * a;
        }
        if (type.equals("/")) {
            return b / a;
        }
        return 0;
    }
}
