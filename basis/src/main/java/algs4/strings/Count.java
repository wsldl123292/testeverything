package algs4.strings;

/**
 * 说明 统计字符次数
 * 作者 LDL
 * 时间 2016/5/17.
 */
public class Count {

    // Do not instantiate.
    private Count() {
    }

    public static void main(String[] args) {
        String a = "0123456789";
        Alphabet alphabet = new Alphabet(a);
        int R = alphabet.R();
        int[] count = new int[R];
        String in = "3233432212312321312546676809087056756756787987978056578980";
        for (char c : in.toCharArray()) {
            System.out.println(c);
            if (alphabet.contains(c)) {
                count[alphabet.toIndex(c)]++;
            }
        }

        for (int i = 0; i < R; i++) {
            System.out.println(alphabet.toChar(i) + " " + count[i]);
        }


        char d = 20013;
        System.out.println(d);
        System.out.println("N".charAt(0));

    }
}
