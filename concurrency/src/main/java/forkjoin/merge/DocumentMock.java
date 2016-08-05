package forkjoin.merge;

import java.util.Random;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-05 16:56
 */
public class DocumentMock {

    private String words[] = {"title", "hello", "goodbye",
            "packt", "java", "thread", "pool", "random", "class", "main"};

    public String[][] generateDocument(int numLines, int numWords, String word) {
        int counter = 0;
        String document[][] = new String[numLines][numWords];
        Random random = new Random();
        for (int i = 0; i < numLines; i++) {
            for (int j = 0; j < numWords; j++) {
                int index = random.nextInt(words.length);
                document[i][j] = words[index];
                if (document[i][j].endsWith(word)) {
                    counter++;
                }
            }
        }

        System.out.println("DocumentMock: The word appares " + counter + " times in the document");

        return document;
    }
}
