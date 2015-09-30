import org.apache.spark.SparkConf
import org.apache.spark.api.java.JavaRDD
import org.apache.spark.api.java.JavaSparkContext
import org.apache.spark.api.java.function.Function
import org.apache.spark.mllib.clustering.KMeansModel
import org.apache.spark.mllib.linalg.Vector
import org.apache.spark.mllib.linalg.Vectors
/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/9/6 14:58
 */
class KmeansTest {

    public static void main(String[] args) {
        System.setProperty("hadoop.home.dir", "F:\\tools\\hadoop-common-2.2.0-bin-master");
        SparkConf conf = new SparkConf().setAppName("K-means Example").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        // Load and parse data
        String path = "F:/tools/spark-1.4.1/data/mllib/kmeans_data.txt";
        JavaRDD<String> data = sc.textFile(path);
        JavaRDD<Vector> parsedData = data.map(
                new Function<String, Vector>() {
                    public Vector call(String s) {
                        String[] sarray = s.split(" ");
                        double[] values = new double[sarray.length];
                        for (int i = 0; i < sarray.length; i++){
                            values[i] = Double.parseDouble(sarray[i]);
                        }
                        return Vectors.dense(values);
                    }
                }
        ).cache();

        // Cluster the data into two classes using KMeans
        KMeansModel kMeansModel = KMeansModel.load(sc.sc(),"D:\\model\\kmeans");
        double WSSSE = kMeansModel.computeCost(parsedData.rdd());
        println(WSSSE);
    }
}
