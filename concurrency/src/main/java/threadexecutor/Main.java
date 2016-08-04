package threadexecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-04 16:58
 */
public class Main {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

//        String username = "";
//        String password = "";
//        UserValidator ldapValidator = new UserValidator("LDAP");
//        UserValidator dbValidator = new UserValidator("DataBase");
//
//        TaskValidator ldapTask = new TaskValidator(ldapValidator,username,password);
//        TaskValidator dbTask = new TaskValidator(dbValidator,username,password);
//
//        List<TaskValidator> taskValidators = new ArrayList<>();
//        taskValidators.add(ldapTask);
//        taskValidators.add(dbTask);
//
//
//
//        String result;
//
//        try {
//            result = executorService.invokeAny(taskValidators);
//            System.out.printf("Main: Result: %s\n",result);
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }
//
//
//        executorService.shutdown();
//        System.out.printf("Main: End of the Execution\n");

        List<TaskAll> taskAlls = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            taskAlls.add(new TaskAll(i+""));
        }

        List<Future<Result>> futures = null;

        try {
            futures = executorService.invokeAll(taskAlls);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown();

        System.out.println("Main: Printing the results.");
        for (int i = 0; i < futures.size(); i++) {
            Future<Result> future = futures.get(i);
            try {
                Result result = future.get();
                System.out.println(result.getName()+": "+result.getValue());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
