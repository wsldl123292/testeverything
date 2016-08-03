package threadsyncutilities.phaser;

import java.util.Date;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-03 16:30
 */
public class Student implements Runnable {
    private Phaser phaser;

    public Student(Phaser phaser) {

        this.phaser = phaser;
    }

    @Override
    public void run() {
        System.out.printf("%s: Has arrived to do the exam. %s\n",Thread.currentThread().getName(),
                new Date());
        phaser.arriveAndAwaitAdvance();

        System.out.printf("%s: Is going to do the first exercise. %s\n",Thread.currentThread().getName(),
                new Date());

        doExercise();

        System.out.printf("%s: Has done the first exercise. %s\n",Thread.currentThread().getName(),
                new Date());

        phaser.arriveAndAwaitAdvance();


        System.out.printf("%s: Is going to do the second exercise. %s\n",Thread.currentThread().getName(),
                new Date());

        doExercise();

        System.out.printf("%s: Has done the second exercise. %s\n",Thread.currentThread().getName(),
                new Date());

        phaser.arriveAndAwaitAdvance();



        System.out.printf("%s: Is going to do the third exercise. %s\n",Thread.currentThread().getName(),
                new Date());

        doExercise();

        System.out.printf("%s: Has finish the exam. %s\n",Thread.currentThread().getName(),
                new Date());


        phaser.arriveAndAwaitAdvance();
    }

    private void doExercise() {
        long duration = (long) (Math.random()*10);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
