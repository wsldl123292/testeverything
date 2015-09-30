package java;

import org.apache.log4j.Logger;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/9/21 17:28
 */
public class SparkJobServerClientFactory {
    /**
     * instance
     */
    private static final SparkJobServerClientFactory INSTANCE = new SparkJobServerClientFactory();

    /**
     * logger
     */
    private static Logger logger = Logger.getLogger(SparkJobServerClientFactory.class);

    /**
     * cache
     */
    private static Map<String, ISparkJobServerClient> jobServerClientCache
            = new ConcurrentHashMap<>();

    /**
     * The default constructor of <code>SparkJobServerClientFactory</code>.
     */
    private SparkJobServerClientFactory() {
    }

    /**
     * Gets the unique instance of <code>SparkJobServerClientFactory</code>.
     * @return the instance of <code>SparkJobServerClientFactory</code>
     */
    public static SparkJobServerClientFactory getInstance() {
        return INSTANCE;
    }

    /**
     * Creates an instance of <code>ISparkJobServerClient</code> with the given url.
     *
     * @param url the url of the target Spark Job Server
     * @return the corresponding <code>ISparkJobServerClient</code> instance
     * @throws SparkJobServerClientException error occurs when trying to create the
     *     target spark job server client
     */
    public ISparkJobServerClient createSparkJobServerClient(String url)
            throws SparkJobServerClientException {
        if (!isValidUrl(url)) {
            throw new SparkJobServerClientException("url不合法，不能创建job.");
        }
        final String sparkJobServerUrl = url.trim();
        ISparkJobServerClient sparkJobServerClient = jobServerClientCache.get(sparkJobServerUrl);
        if (null == sparkJobServerClient) {
            sparkJobServerClient = new SparkJobServerClientImpl(url);
            jobServerClientCache.put(url, sparkJobServerClient);
        }
        return sparkJobServerClient;
    }

    /**
     * Checks the given url is valid or not.
     *
     * @param url the url to be checked
     * @return true if it is valid, false otherwise
     */
    private boolean isValidUrl(String url) {
        if (url == null || url.trim().length() <= 0) {
            logger.error("url不能为空.");
            return false;
        }
        try {
            new URL(url);
        } catch (MalformedURLException me) {
            logger.error(url + " 不可用.", me);
        }
        return true;
    }
}
