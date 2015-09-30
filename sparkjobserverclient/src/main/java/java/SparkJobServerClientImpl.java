package java;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.*;

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/9/21 17:29
 */
public class SparkJobServerClientImpl implements ISparkJobServerClient {

    /**
     * 定义日志
     */
    private static Logger logger = Logger.getLogger(SparkJobServerClientImpl.class);
    /**
     * 默认上传buffer_size
     */
    private static final int BUFFER_SIZE = 512 * 1024;
    /**
     * jobServer地址
     */
    private String jobServerUrl;
    /**
     * http请求
     */
    private HttpClient httpClient;

    /**
     * Constructs an instance of <code>SparkJobServerClientImpl</code>
     * with the given spark job server url.
     *
     * @param jobServerUrl a url pointing to a existing spark job server
     */
    SparkJobServerClientImpl(String jobServerUrl) {
        if (!jobServerUrl.endsWith("/")) {
            jobServerUrl = jobServerUrl + "/";
        }
        this.jobServerUrl = jobServerUrl;
        this.httpClient = new DefaultHttpClient();
    }


    /**
     * 获取jars的信息
     *
     * @return jar的结合
     * @throws SparkJobServerClientException 异常
     */
    public List<SparkJobJarInfo> getJars() throws SparkJobServerClientException {
        final List<SparkJobJarInfo> sparkJobJarInfos = new ArrayList<>();
        try {
            final HttpGet getMethod = new HttpGet(jobServerUrl + "jars");
            final HttpResponse response = httpClient.execute(getMethod);
            final int statusCode = response.getStatusLine().getStatusCode();
            final String resContent = getResponseContent(response.getEntity());
            if (statusCode == HttpStatus.SC_OK) {
                /*JSONObject jsonObj = JSONObject.fromObject(resContent);
                Iterator<?> keyIter = jsonObj.keys();
                while (keyIter.hasNext()) {
                    String jarName = (String)keyIter.next();
                    String uploadedTime = (String)jsonObj.get(jarName);
                    SparkJobJarInfo sparkJobJarInfo = new SparkJobJarInfo();
                    sparkJobJarInfo.setJarName(jarName);
                    sparkJobJarInfo.setUploadedTime(uploadedTime);
                    sparkJobJarInfos.add(sparkJobJarInfo);
                }*/
                final JSONObject jsonObj = JSONObject.parseObject(resContent);
                final Set<String> keySet = jsonObj.keySet();
                for (String key : keySet) {
                    final String uploadedTime = (String) jsonObj.get(key);
                    final SparkJobJarInfo sparkJobJarInfo = new SparkJobJarInfo();
                    sparkJobJarInfo.setJarName(key);
                    sparkJobJarInfo.setUploadedTime(uploadedTime);
                    sparkJobJarInfos.add(sparkJobJarInfo);
                }
            } else {
                logError(statusCode, resContent, true);
            }
        } catch (Exception e) {
            processException("获取jars信息时出现异常:", e);
        }
        return sparkJobJarInfos;
    }

    /**
     * 添加jar包
     *
     * @param jarData the instance of <code>InputStream</code> contains the
     *                contents of the target jar file to be uploaded
     * @param appName the application name under which the related Spark Job
     *                is about to run, meanwhile the application name also be the alias
     *                name of the uploaded jar file.
     * @return boolean
     * @throws SparkJobServerClientException 异常
     */
    /*public boolean uploadSparkJobJar(InputStream jarData, String appName)
            throws SparkJobServerClientException {
        if (jarData == null || appName == null || appName.trim().length() == 0) {
            throw new SparkJobServerClientException("参数不合法.");
        }
        final HttpPost postMethod = new HttpPost(jobServerUrl + "jars/" + appName);

        final byte[] contents = new byte[BUFFER_SIZE];
        int len;
        final StringBuilder buff = new StringBuilder();
        try {
            while ((len = jarData.read(contents)) > 0) {
                buff.append(new String(contents, 0, len));
            }
            final ByteArrayEntity entity = new ByteArrayEntity(buff.toString().getBytes());
            postMethod.setEntity(entity);
            entity.setContentType("application/java-archive");
            final HttpResponse response = httpClient.execute(postMethod);
            final int statusCode = response.getStatusLine().getStatusCode();
            getResponseContent(response.getEntity());
            if (statusCode == HttpStatus.SC_OK) {
                return true;
            }
        } catch (Exception e) {
            logger.error("上传jar包是出现异常:", e);
        } finally {
            closeStream(jarData);
        }
        return false;
    }*/

    /**
     * 添加jar包
     *
     * @param jarFile the jar file
     * @param appName the application name under which the related Spark Job
     *                is about to run, meanwhile the application name also be the alias
     *                name of the uploaded jar file.
     * @return boolean
     * @throws SparkJobServerClientException 异常
     */
    public boolean uploadSparkJobJar(File jarFile, String appName)
            throws SparkJobServerClientException {
        if (jarFile == null || !jarFile.getName().endsWith(".jar") ||
                appName == null || appName.trim().length() == 0) {
            throw new SparkJobServerClientException("参数不合法.");
        }
        final HttpPost postMethod = new HttpPost(jobServerUrl + "jars/" + appName);
        final FileEntity fileEntity = new FileEntity(jarFile);
        try {
            postMethod.setEntity(fileEntity);
            fileEntity.setContentType("application/java-archive");
            final HttpResponse response = httpClient.execute(postMethod);
            final int statusCode = response.getStatusLine().getStatusCode();
            getResponseContent(response.getEntity());
            if (statusCode == HttpStatus.SC_OK) {
                return true;
            }
        } catch (IOException e) {
            logger.error("上传jar包是出现异常:", e);
            e.printStackTrace();
        }
        return false;
        /*InputStream jarIn;
        try {
            jarIn = new FileInputStream(jarFile);
        } catch (FileNotFoundException fnfe) {
            final String errorMsg = "读取jar包流时出现异常";
            logger.error(errorMsg, fnfe);
            throw new SparkJobServerClientException(errorMsg, fnfe);
        }
        return uploadSparkJobJar(jarIn, appName);*/
    }

    /**
     * 获取contexts
     *
     * @return contexts
     * @throws SparkJobServerClientException 异常
     */
    public List<String> getContexts() throws SparkJobServerClientException {
        final List<String> contexts = new ArrayList<>();
        try {
            final HttpGet getMethod = new HttpGet(jobServerUrl + "contexts");
            final HttpResponse response = httpClient.execute(getMethod);
            final int statusCode = response.getStatusLine().getStatusCode();
            final String resContent = getResponseContent(response.getEntity());
            if (statusCode == HttpStatus.SC_OK) {
                //JSONArray jsonArray = JSONArray.fromObject(resContent);
                final JSONArray jsonArray = JSONArray.parseArray(resContent);
                final Iterator<?> iter = jsonArray.iterator();
                while (iter.hasNext()) {
                    contexts.add((String) iter.next());
                }

            } else {
                logError(statusCode, resContent, true);
            }
        } catch (Exception e) {
            processException("获取contexts信息时出现异常:", e);
        }
        return contexts;
    }

    /**
     * 创建context
     *
     * @param contextName the name of the new context to be created, it should be not null
     *                    and should begin with letter.
     * @param params      a map containing the key-value pairs appended to appoint the context
     *                    settings if there is a need to configure the new created context, or null indicates
     *                    the new context with the default configuration
     * @return boolean
     * @throws SparkJobServerClientException 异常
     */
    public boolean createContext(String contextName, Map<String, String> params)
            throws SparkJobServerClientException {
        try {
            //TODO add a check for the validation of contextName naming
            if (!isNotEmpty(contextName)) {
                throw new SparkJobServerClientException("context名称不能为空.");
            }
            final StringBuilder postUrlBuff = new StringBuilder(jobServerUrl);
            postUrlBuff.append("contexts/").append(contextName);
            if (params != null && !params.isEmpty()) {
                postUrlBuff.append('?');
                int num = params.size();
                for (String key : params.keySet()) {
                    postUrlBuff.append(key).append('=').append(params.get(key));
                    num--;
                    if (num > 0) {
                        postUrlBuff.append('&');
                    }
                }

            }
            final HttpPost postMethod = new HttpPost(postUrlBuff.toString());
            final HttpResponse response = httpClient.execute(postMethod);
            final int statusCode = response.getStatusLine().getStatusCode();
            final String resContent = getResponseContent(response.getEntity());
            if (statusCode == HttpStatus.SC_OK) {
                return true;
            } else {
                logError(statusCode, resContent, false);
            }
        } catch (Exception e) {
            processException("创建context时出现异常:", e);
        }
        return false;
    }

    /**
     * 删除context
     *
     * @param contextName the name of the target context to be deleted, it should be not null
     *                    and should begin with letter.
     * @return boolean
     * @throws SparkJobServerClientException 异常
     */
    public boolean deleteContext(String contextName)
            throws SparkJobServerClientException {
        try {
            //TODO add a check for the validation of contextName naming
            if (!isNotEmpty(contextName)) {
                throw new SparkJobServerClientException("contextName不能为空.");
            }

            final HttpDelete deleteMethod = new HttpDelete(jobServerUrl + "contexts/" + contextName);
            final HttpResponse response = httpClient.execute(deleteMethod);
            final int statusCode = response.getStatusLine().getStatusCode();
            final String resContent = getResponseContent(response.getEntity());
            if (statusCode == HttpStatus.SC_OK) {
                return true;
            } else {
                logError(statusCode, resContent, false);
            }
        } catch (Exception e) {
            processException("删除context时出现异常:", e);
        }
        return false;
    }

    /**
     * 获取任务信息
     *
     * @return jobs
     * @throws SparkJobServerClientException 异常
     */
    public List<SparkJobInfo> getJobs() throws SparkJobServerClientException {
        final List<SparkJobInfo> sparkJobInfos = new ArrayList<>();
        try {
            final HttpGet getMethod = new HttpGet(jobServerUrl + "jobs");
            final HttpResponse response = httpClient.execute(getMethod);
            final int statusCode = response.getStatusLine().getStatusCode();
            final String resContent = getResponseContent(response.getEntity());
            if (statusCode == HttpStatus.SC_OK) {
                //JSONArray jsonArray = JSONArray.fromObject(resContent);
                final JSONArray jsonArray = JSONArray.parseArray(resContent);
                final Iterator<?> iter = jsonArray.iterator();
                while (iter.hasNext()) {
                    final JSONObject jsonObj = (JSONObject) iter.next();
                    final SparkJobInfo jobInfo = new SparkJobInfo();
                    jobInfo.setDuration(jsonObj.getString(SparkJobInfo.INFO_KEY_DURATION));
                    jobInfo.setClassPath(jsonObj.getString(SparkJobInfo.INFO_KEY_CLASSPATH));
                    jobInfo.setStartTime(jsonObj.getString(SparkJobInfo.INFO_KEY_START_TIME));
                    jobInfo.setContext(jsonObj.getString(SparkJobBaseInfo.INFO_KEY_CONTEXT));
                    jobInfo.setStatus(jsonObj.getString(SparkJobBaseInfo.INFO_KEY_STATUS));
                    jobInfo.setJobId(jsonObj.getString(SparkJobBaseInfo.INFO_KEY_JOB_ID));
                    setErrorDetails(SparkJobBaseInfo.INFO_KEY_RESULT, jsonObj, jobInfo);
                    sparkJobInfos.add(jobInfo);
                }
            } else {
                logError(statusCode, resContent, true);
            }
        } catch (Exception e) {
            processException("或许job信息时出错:", e);
        }
        return sparkJobInfos;
    }

    /**
     * 启动任务
     *
     * @param data   contains the the data processed by the target job.
     *               <p>If it is null, it means the target spark job doesn't needs any data set
     *               in the job configuration.
     *               <p>If it is not null, the format should be like a key-value pair, such as
     *               <code>dataKey=dataValue</code>, what the dataKey is determined by the
     *               one used in the target spark job main class which is assigned by
     *               ISparkJobServerClientConstants.PARAM_CLASS_PATH.
     * @param params a non-null map containing parameters to start the job.
     *               the key should be the following ones:
     *               i. <code>ISparkJobServerClientConstants.PARAM_APP_NAME</code>, necessary
     *               one and should be one of the existing name in the calling of <code>GET /jars</code>.
     *               That means the appName is the alias name of the uploaded spark job jars.
     *               <p>ii.<code>ISparkJobServerClientConstants.PARAM_CLASS_PATH</code>, necessary one
     *               <p>iii.<code>ISparkJobServerClientConstants.PARAM_CONTEXT</code>, optional one
     *               <p>iv.<code>ISparkJobServerClientConstants.PARAM_SYNC</code>, optional one
     * @return SparkJobResult
     * @throws SparkJobServerClientException 异常
     */
    public SparkJobResult startJob(String data, Map<String, String> params) throws SparkJobServerClientException {
        try {
            if (params == null || params.isEmpty()) {
                throw new SparkJobServerClientException("参数不能为空.");
            }
            if (params.containsKey(ISparkJobServerClientConstants.PARAM_APP_NAME) &&
                    params.containsKey(ISparkJobServerClientConstants.PARAM_CLASS_PATH)) {
                final StringBuilder postUrlBuff = new StringBuilder(jobServerUrl);
                postUrlBuff.append("jobs?");
                int num = params.size();
                for (String key : params.keySet()) {
                    postUrlBuff.append(key).append('=').append(params.get(key));
                    num--;
                    if (num > 0) {
                        postUrlBuff.append('&');
                    }
                }
                final HttpPost postMethod = new HttpPost(postUrlBuff.toString());
                if (data != null) {
                    final StringEntity strEntity = new StringEntity(data);
                    strEntity.setContentEncoding("UTF-8");
                    strEntity.setContentType("text/plain");
                    postMethod.setEntity(strEntity);
                }

                final HttpResponse response = httpClient.execute(postMethod);
                final String resContent = getResponseContent(response.getEntity());
                final int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == HttpStatus.SC_OK) {
                    return parseResult(resContent);
                } else {
                    logError(statusCode, resContent, true);
                }
            } else {
                throw new SparkJobServerClientException("参数应该包含appName和classPath");
            }
        } catch (Exception e) {
            processException("当启动一个新job时出现异常:", e);
        }
        return null;
    }

    /**
     * 根据jobId获取job结果
     *
     * @param jobId the id of the target job
     * @return SparkJobResult
     * @throws SparkJobServerClientException 异常
     */
    public SparkJobResult getJobResult(String jobId) throws SparkJobServerClientException {
        try {
            if (!isNotEmpty(jobId)) {
                throw new SparkJobServerClientException("jobId不能为空.");
            }
            final HttpGet getMethod = new HttpGet(jobServerUrl + "jobs/" + jobId);
            final HttpResponse response = httpClient.execute(getMethod);
            final String resContent = getResponseContent(response.getEntity());
            final int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                return parseResult(resContent);
            } else if (statusCode == HttpStatus.SC_NOT_FOUND) {
                return new SparkJobResult(resContent);
            } else {
                logError(statusCode, resContent, true);
            }
        } catch (Exception e) {
            processException("获取目标job时出现异常:", e);
        }
        return null;
    }

    /**
     * 根据jobId获取job配置信息
     *
     * @param jobId the id of the target job
     * @return SparkJobConfig
     * @throws SparkJobServerClientException 异常
     */
    public SparkJobConfig getConfig(String jobId) throws SparkJobServerClientException {
        try {
            if (!isNotEmpty(jobId)) {
                throw new SparkJobServerClientException("jobId不能为空.");
            }
            final HttpGet getMethod = new HttpGet(jobServerUrl + "jobs/" + jobId + "/config");
            final HttpResponse response = httpClient.execute(getMethod);
            final String resContent = getResponseContent(response.getEntity());
            final SparkJobConfig jobConfg = new SparkJobConfig();
            /*JSONObject jsonObj = JSONObject.fromObject(resContent);
            Iterator<?> keyIter = jsonObj.keys();
            while (keyIter.hasNext()) {
                String key = (String) keyIter.next();
                jobConfg.putConfigItem(key, jsonObj.get(key));
            }*/
            final JSONObject jsonObject = JSONObject.parseObject(resContent);
            final Set<String> keySet = jsonObject.keySet();
            for (String key : keySet) {
                jobConfg.putConfigItem(key, jsonObject.get(key));
            }
            return jobConfg;
        } catch (Exception e) {
            processException("获取目标job配置时出现异常:", e);
        }
        return null;
    }

    /**
     * 停止
     */
    public void stop() {
        this.httpClient.getConnectionManager().shutdown();
    }

    /**
     * 获取返回的内容
     *
     * @param entity the <code>HttpEntity</code> instance holding the http response content
     * @return the corresponding response content
     */
    protected String getResponseContent(HttpEntity entity) {
        final byte[] buff = new byte[BUFFER_SIZE];
        final StringBuilder contents = new StringBuilder();
        InputStream in = null;
        try {
            in = entity.getContent();
            final BufferedInputStream bis = new BufferedInputStream(in);
            int readBytes;
            while ((readBytes = bis.read(buff)) != -1) {
                contents.append(new String(buff, 0, readBytes));
            }
        } catch (Exception e) {
            logger.error("读取返回结果时出现异常", e);
        } finally {
            closeStream(in);
        }
        return contents.toString().trim();
    }

    /**
     * 关闭stream.
     *
     * @param stream the input/output stream to be closed
     */
    protected void closeStream(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException ioe) {
                logger.error("关闭stream时出现异常:", ioe);
            }
        } else {
            logger.error("stream为null");
        }
    }

    /**
     * Handles the given exception with specific error message, and
     * generates a corresponding <code>SparkJobServerClientException</code>.
     *
     * @param errorMsg the corresponding error message
     * @param e        the exception to be handled
     * @throws SparkJobServerClientException the corresponding transformed
     *                                       <code>SparkJobServerClientException</code> instance
     */
    protected void processException(String errorMsg, Exception e) throws SparkJobServerClientException {
        if (e instanceof SparkJobServerClientException) {
            throw (SparkJobServerClientException) e;
        }
        logger.error(errorMsg, e);
        throw new SparkJobServerClientException(errorMsg, e);
    }

    /**
     * 判断字符串是否为空
     *
     * @param value the string value to be checked
     * @return true indicates it is not empty, false otherwise
     */
    protected boolean isNotEmpty(String value) {
        return value != null && !value.isEmpty();
    }

    /**
     * Logs the response information when the status is not 200 OK,
     * and throws an instance of <code>SparkJobServerClientException<code>.
     *
     * @param errorStatusCode error status code
     * @param msg             the message to indicates the status, it can be null
     * @param throwable       true indicates throws an instance of <code>SparkJobServerClientException</code>
     *                        with corresponding error message, false means only log the error message.
     * @throws SparkJobServerClientException containing the corresponding error message
     */
    private void logError(int errorStatusCode, String msg, boolean throwable) throws SparkJobServerClientException {
        final StringBuilder msgBuff = new StringBuilder("Spark Job Server ");
        msgBuff.append(jobServerUrl).append(" response ").append(errorStatusCode);
        if (null != msg) {
            msgBuff.append(" ").append(msg);
        }
        final String errorMsg = msgBuff.toString();
        logger.error(errorMsg);
        if (throwable) {
            throw new SparkJobServerClientException(errorMsg);
        }
    }

    /**
     * 错误信息
     *
     * @param key           the key contains the error details
     * @param parnetJsonObj the parent <code>JSONObject</code> instance
     * @param jobErrorInfo  the <code>SparkJobErrorInfo</code> instance to be set information
     */
    private void setErrorDetails(String key, JSONObject parnetJsonObj, SparkJobBaseInfo jobErrorInfo) {
        if (parnetJsonObj.containsKey(key)) {
            final JSONObject resultJson = parnetJsonObj.getJSONObject(key);
            if (resultJson.containsKey(SparkJobInfo.INFO_KEY_RESULT_MESSAGE)) {
                jobErrorInfo.setMessage(resultJson.getString(SparkJobInfo.INFO_KEY_RESULT_MESSAGE));
            }
            if (resultJson.containsKey(SparkJobInfo.INFO_KEY_RESULT_ERROR_CLASS)) {
                jobErrorInfo.setErrorClass(resultJson.getString(SparkJobInfo.INFO_KEY_RESULT_ERROR_CLASS));
            }
            if (resultJson.containsKey(SparkJobInfo.INFO_KEY_RESULT_STACK)) {
                final JSONArray stackJsonArray = resultJson.getJSONArray(SparkJobInfo.INFO_KEY_RESULT_STACK);
                final String[] stack = new String[stackJsonArray.size()];
                for (int i = 0; i < stackJsonArray.size(); i++) {
                    stack[i] = stackJsonArray.getString(i);
                }
                jobErrorInfo.setStack(stack);
            }
        }
    }

    /**
     * 解析resContent生成SparkJobResult
     *
     * @param resContent the content of a http response
     * @return the corresponding <code>SparkJobResult</code> instance
     * @throws Exception error occurs when parsing the http response content
     */
    private SparkJobResult parseResult(String resContent) throws Exception {
        //JSONObject jsonObj = JSONObject.fromObject(resContent);
        final JSONObject jsonObj = JSONObject.parseObject(resContent);
        final SparkJobResult jobResult = new SparkJobResult(resContent);
        jobResult.setStatus(jsonObj.getString(SparkJobBaseInfo.INFO_KEY_STATUS));
        if (SparkJobBaseInfo.INFO_STATUS_OK.equals(jobResult.getStatus())) {
            //Job finished with results
            jobResult.setResult(jsonObj.get(SparkJobBaseInfo.INFO_KEY_RESULT).toString());
        } else if (containsAsynjobStatus(jsonObj)) {
            //asynchronously started job only with status information
            setAsynjobStatus(jobResult, jsonObj);
        } else if (containsErrorInfo(jsonObj)) {
            String errorKey = null;
            if (jsonObj.containsKey(SparkJobBaseInfo.INFO_STATUS_ERROR)) {
                errorKey = SparkJobBaseInfo.INFO_STATUS_ERROR;
            } else if (jsonObj.containsKey(SparkJobBaseInfo.INFO_KEY_RESULT)) {
                errorKey = SparkJobBaseInfo.INFO_KEY_RESULT;
            }
            //Job failed with error details
            setErrorDetails(errorKey, jsonObj, jobResult);
        } else {
            //Other unknown kind of value needs application to parse itself
            /*Iterator<?> keyIter = jsonObj.keys();
            while (keyIter.hasNext()) {
                String key = (String) keyIter.next();
                if (SparkJobInfo.INFO_KEY_STATUS.equals(key)) {
                    continue;
                }
                jobResult.putExtendAttribute(key, jsonObj.get(key));
            }*/

            final Set<String> keySet = jsonObj.keySet();
            for (String key : keySet) {
                if (SparkJobInfo.INFO_KEY_STATUS.equals(key)) {
                    continue;
                }
                jobResult.putExtendAttribute(key, jsonObj.get(key));
            }
        }
        return jobResult;
    }

    /**
     * Judges the given json object contains the error information of a
     * spark job or not.
     *
     * @param jsonObj the <code>JSONObject</code> instance to be checked.
     * @return true if it contains the error information, false otherwise
     */
    private boolean containsErrorInfo(JSONObject jsonObj) {
        return SparkJobBaseInfo.INFO_STATUS_ERROR.equals(jsonObj.getString(SparkJobBaseInfo.INFO_KEY_STATUS));
    }


    /**
     * Judges the given json object contains the status information of a asynchronous
     * started spark job or not.
     *
     * @param jsonObj the <code>JSONObject</code> instance to be checked.
     * @return true if it contains the status information of a asynchronous
     * started spark job, false otherwise
     */
    private boolean containsAsynjobStatus(JSONObject jsonObj) {
        return jsonObj != null && jsonObj.containsKey(SparkJobBaseInfo.INFO_KEY_STATUS) &&
                SparkJobBaseInfo.INFO_STATUS_STARTED.equals(jsonObj.getString(SparkJobBaseInfo.INFO_KEY_STATUS)) &&
                jsonObj.containsKey(SparkJobBaseInfo.INFO_KEY_RESULT);
    }

    /**
     * Sets the status information of a asynchronous started spark job to the given
     * job result instance.
     *
     * @param jobResult the <code>SparkJobResult</code> instance to be set the status information
     * @param jsonObj   the <code>JSONObject</code> instance holds the status information
     */
    private void setAsynjobStatus(SparkJobResult jobResult, JSONObject jsonObj) {
        final JSONObject resultJsonObj = jsonObj.getJSONObject(SparkJobBaseInfo.INFO_KEY_RESULT);
        jobResult.setContext(resultJsonObj.getString(SparkJobBaseInfo.INFO_KEY_CONTEXT));
        jobResult.setJobId(resultJsonObj.getString(SparkJobBaseInfo.INFO_KEY_JOB_ID));
    }
}
