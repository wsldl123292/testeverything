package threadsyncutilities.phaser;

import java.util.concurrent.Phaser;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-03 16:23
 */
public class MyPhaser extends Phaser {

    public MyPhaser(int parties) {
        super(parties);
    }

    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        switch (phase) {
            case 0:
                return studentsArrived();
            case 1:
                return finishFirstExercise();
            case 2:
                return finishSecondExercise();
            case 3:
                return finishExam();
            default:
                return true;
        }
    }

    private boolean finishExam() {
        System.out.println("Phaser: All the students have finished the exam.");
        System.out.println("Phaser: Thank you for your time.");
        return true;
    }

    private boolean finishSecondExercise() {
        System.out.println("Phaser: All the students have finished the second exercise.");
        System.out.println("Phaser: It's time for the third one.");
        return false;
    }

    private boolean finishFirstExercise() {

        System.out.println("Phaser: All the students have finished the first exercise.");
        System.out.println("Phaser: It's time for the second one.");
        return false;
    }

    private boolean studentsArrived() {
        System.out.printf("Phaser: The exam are going to start." +
                "The stuents are ready.\n");
        System.out.printf("Phaser: We have %d students.\n",getRegisteredParties());
        return false;
    }


}
