package forkjoin.sync;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-05 19:32
 */
public class FolderProcesser extends RecursiveTask<List<String>> {

    private String path;
    private String extension;

    public FolderProcesser(String path, String extension) {
        this.path = path;
        this.extension = extension;
    }

    @Override
    protected List<String> compute() {
        List<String> list = new ArrayList<>();
        List<FolderProcesser> tasks = new ArrayList<>();
        File file = new File(path);
        File[] content = file.listFiles();
        if (content != null) {
            for (File aContent : content) {
                if (aContent.isDirectory()) {
                    FolderProcesser folderProcesser = new FolderProcesser(aContent.getAbsolutePath(),
                            extension);
                    folderProcesser.fork();
                    tasks.add(folderProcesser);
                } else {
                    if (checkFile(aContent.getName())) {
                        list.add(aContent.getAbsolutePath());
                    }
                }
            }
        }

        if (tasks.size() > 50) {
            System.out.printf("%s: %d tasks run.\n", file.getAbsolutePath(), tasks.size());
        }

        addResultsFromTasks(list, tasks);
        return list;
    }

    private boolean checkFile(String name) {
        return name.endsWith(extension);
    }

    private void addResultsFromTasks(List<String> list, List<FolderProcesser> tasks) {

        for (FolderProcesser item : tasks) {
            list.addAll(item.join());
        }
    }
}
