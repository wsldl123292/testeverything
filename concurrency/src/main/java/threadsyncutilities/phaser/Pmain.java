package threadsyncutilities.phaser;


import java.util.concurrent.Phaser;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-03 15:56
 */
public class Pmain {

    public static void main(String[] args) {
//        Phaser phaser = new Phaser(3);
//
//        FileSearch system = new FileSearch("/Users/ldl/Documents/develop/project/github/testeverything/concurrency/src/main/java/synchronization","java",phaser);
//        FileSearch apps = new FileSearch("/Users/ldl/Documents/develop/project/github/testeverything/concurrency/src/main/java/threadmanagement","java",phaser);
//        FileSearch document = new FileSearch("/Users/ldl/Documents/develop/project/github/testeverything/concurrency/src/main/java/threadsyncutilities","java",phaser);
//
//        Thread thread1 = new Thread(system,"System");
//        thread1.start();
//
//        Thread thread2 = new Thread(apps,"Apps");
//        thread2.start();
//
//
//        Thread thread3 = new Thread(document,"documents");
//        thread3.start();
//
//        try{
//            thread1.join();
//            thread2.join();
//            thread3.join();
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
//
//        System.out.println("Terminated: "+phaser.isTerminated());



        MyPhaser myPhaser = new MyPhaser(6);
        Student[] students = new Student[5];
        for (int i = 0; i < students.length; i++) {
            students[i] = new Student(myPhaser);
            //myPhaser.register();
        }

        Thread[] threads = new Thread[students.length];
        for (int i = 0; i < students.length; i++) {
            threads[i] = new Thread(students[i],"Student "+i);
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("Main: The phaser has finished: %s.\n",myPhaser.isTerminated());

    }
}
