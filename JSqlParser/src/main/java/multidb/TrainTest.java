package multidb;

import groovy.lang.GroovyShell;

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/9/6 14:07
 */
public class TrainTest {

    public static void main(String[] args) {
        final GroovyShell gs = new GroovyShell();

        final Object obj = gs.evaluate("import org.apache.spark.SparkConf;import org.apache.spark.api.java.JavaRDD;import org.apache.spark.api.java.JavaSparkContext;" +
                "import org.apache.spark.api.java.function.Function;import org.apache.spark.mllib.clustering.KMeans;import org.apache.spark.mllib.clustering.KMeansModel;" +
                "import org.apache.spark.mllib.linalg.Vectors;import org.apache.spark.mllib.linalg.Vector;" +
                "System.setProperty(\"hadoop.home.dir\", \"F:/tools/hadoop-common-2.2.0-bin-master\");" +
                "SparkConf conf = new SparkConf().setAppName(\"K-means Example\").setMaster(\"local\");" +
                "JavaSparkContext sc = new JavaSparkContext(conf);" +
                "String path = \"F:/tools/spark-1.4.1/data/mllib/kmeans_data.txt\";" +
                "JavaRDD<String> data = sc.textFile(path);" +
                "JavaRDD<Vector> parsedData = data.map(\n" +
                "                new Function<String, Vector>() {\n" +
                "                    public Vector call(String s) {\n" +
                "                        String[] sarray = s.split(\" \");\n" +
                "                        double[] values = new double[sarray.length];\n" +
                "                        for (int i = 0; i < sarray.length; i++){\n" +
                "                            values[i] = Double.parseDouble(sarray[i]);\n" +
                "                        }\n" +
                "                        return Vectors.dense(values);\n" +
                "                    }\n" +
                "                }\n" +
                "        ).cache();" +
                "KMeansModel kMeansModel = KMeansModel.load(sc.sc(),\"D:/model/kmeans\");" +
                "double WSSSE = kMeansModel.computeCost(parsedData.rdd());return WSSSE;return WSSSE;");
        System.out.println("spark....." + obj);
    }
}
