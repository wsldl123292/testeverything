package threadsyncutilities.exchanger;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-03 18:06
 */
public class Emain {

    public static void main(String[] args) {
        List<String> buffer1 = new ArrayList<>();
        List<String> buffer2 = new ArrayList<>();

        Exchanger<List<String>> exchanger = new Exchanger<>();

        Producer producer = new Producer(buffer1,exchanger);
        Consumer consumer = new Consumer(exchanger,buffer2);

        Thread thread1 = new Thread(producer);
        Thread thread2 = new Thread(consumer);

        thread1.start();
        thread2.start();
    }
}
