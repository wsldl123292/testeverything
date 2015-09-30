package java;

import java.io.File;
import java.io.IOException;

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/9/21 17:06
 */
public class JobServerClient {
    public static void main(String[] args) throws IOException {

        ISparkJobServerClient client = null;
        try {
            client = SparkJobServerClientFactory.getInstance().createSparkJobServerClient("http://192.168.1.212:8090/");
            //GET /jars
            /*final List<SparkJobJarInfo> jarInfos = client.getJars();
            System.out.println("Current jars:");
            for (SparkJobJarInfo sparkJobJarInfo : jarInfos) {
                System.out.println(sparkJobJarInfo.getJarName() + "..." + sparkJobJarInfo.getUploadedTime());
            }*/

            //POST /jars/<appName>
            boolean upFlag = client.uploadSparkJobJar(new
                    File("F:\\GitHub\\everythingtest\\sparkhbase\\target\\sparkhbase-1.0.jar"), "sparktest2");
            System.out.println(upFlag);

            //GET /contexts
            /*final List<String> contexts = client.getContexts();
            System.out.println("Current contexts:");
            for (String cxt: contexts) {
                System.out.println(cxt);
            }*/

            //POST /contexts/<name>--Create context with name ctxTest and null parameter
            //client.createContext("ctxTest", null);
            //POST /contexts/<name>--Create context with parameters
            /*final Map<String, String> params = new HashMap<>();
            params.put(ISparkJobServerClientConstants.PARAM_MEM_PER_NODE, "512m");
            params.put(ISparkJobServerClientConstants.PARAM_NUM_CPU_CORES, "1");
            boolean ctxflag = client.createContext("cxtTest3", params);
            System.out.println(ctxflag);*/
            //DELETE /contexts/<name>
            //client.deleteContext("cxtTest2");

            //GET /jobs
            /*final List<SparkJobInfo> jobInfos = client.getJobs();
            System.out.println("Current jobs:");
            for (SparkJobInfo jobInfo: jobInfos) {
                System.out.println(jobInfo);
            }*/

            //Post /jobs---Create a new job
            /*final Map<String, String> params = new HashMap<>();
            params.put(ISparkJobServerClientConstants.PARAM_APP_NAME, "sparktest");
            params.put(ISparkJobServerClientConstants.PARAM_CLASS_PATH, "WordCountExample");
            //1.start a spark job asynchronously and just get the status information
            SparkJobResult result = client.startJob("input.string= fdsafd dfsf blullkfdsoflaw fsdfs", params);
            System.out.println(result);*/

            //2.start a spark job synchronously and wait until the result
            /*params.put(ISparkJobServerClientConstants.PARAM_CONTEXT, "cxtTest2");
            params.put(ISparkJobServerClientConstants.PARAM_SYNC, "true");
            result = client.startJob("input.string= fdsafd dfsf blullkfdsoflaw fsdffdsfsfs", params);
            System.out.println(result);

            //GET /jobs/<jobId>---Gets the result or status of a specific job
            result = client.getJobResult("fdsfsfdfwfef");
            System.out.println(result);

            //GET /jobs/<jobId>/config - Gets the job configuration
            final SparkJobConfig jobConfig = client.getConfig("fdsfsfdfwfef");
            System.out.println(jobConfig);*/
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (client != null) {
                client.stop();
            }
        }
    }


}
