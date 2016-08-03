package threadsyncutilities.phaser;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-03 15:41
 */
public class FileSearch implements Runnable {

    private String initPath;
    private String end;
    private List<String> results;
    private Phaser phaser;

    public FileSearch(String initPath, String end, Phaser phaser) {
        this.initPath = initPath;
        this.end = end;
        this.results = new ArrayList<>();
        this.phaser = phaser;
    }

    @Override
    public void run() {
        phaser.arriveAndAwaitAdvance();
        System.out.printf("%s: Starting.\n",Thread.currentThread().getName());
        File file = new File(initPath);
        if(file.isDirectory()){
            directoryProcess(file);
        }
        if(!checkResults()){
            return;
        }

        filterResults();
        if(!checkResults()){
            return;
        }

        showInfo();
        phaser.arriveAndDeregister();
        System.out.printf("%s: Work completed.\n",Thread.currentThread().getName());
    }


    private void directoryProcess(File file) {
        File[] list = file.listFiles();
        if (list != null) {
            for (File aList : list) {
                if (aList.isDirectory()) {
                    directoryProcess(aList);
                } else {
                    fileProcess(aList);
                }
            }
        }
    }

    private void fileProcess(File file) {
        if (file.getName().endsWith(end)) {
            results.add(file.getAbsolutePath());
        }
    }

    private void filterResults() {
        List<String> newResults = new ArrayList<>();
        long actualDate = new Date().getTime();
        for (String result : results) {
            File file = new File(result);
            long fileDate = file.lastModified();

            if (actualDate - fileDate < TimeUnit.MILLISECONDS.convert(5, TimeUnit.DAYS)) {
                newResults.add(result);
            }
        }

        results = newResults;
    }

    private boolean checkResults() {
        if (results.isEmpty()) {
            System.out.printf("%s: Phase %d: 0 results.\n", Thread.currentThread().getName(), phaser.getPhase());
            System.out.printf("%s: Phase %d: 0 End.\n", Thread.currentThread().getName(), phaser.getPhase());
            phaser.arriveAndDeregister();
            return false;
        } else {
            System.out.printf("%s: Phase %d: %d results.\n",
                    Thread.currentThread().getName(), phaser.getPhase(),results.size());
            phaser.arriveAndAwaitAdvance();
            return true;
        }
    }


    private void showInfo(){
        for (String result : results) {
            File file = new File(result);
            System.out.printf("%s: %s\n", Thread.currentThread().getName(), file.getAbsolutePath());
        }

        phaser.arriveAndAwaitAdvance();
    }
}
