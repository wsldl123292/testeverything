import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.Collections;
import java.util.List;

/**
 * 作者: LDL
 * 说明:
 * 时间: 2015/9/13 12:22
 */
public class UserPurchaseHistoryJava {
    public static void main(String[] args) {
        System.setProperty("hadoop.home.dir","F:\\data\\hadoop-common-2.2.0-bin-master");
        SparkConf conf = new SparkConf().setAppName("UserPurchaseHistory").setMaster("local");
        JavaSparkContext jsc = new JavaSparkContext(conf);

        JavaRDD<String[]> data = jsc.textFile("F:\\github\\everythingtest\\mlspark\\src\\main\\resources\\UserPurchaseHistory.csv").map(s -> s.split(","));

        /**
         * 购买人数
         */
        long numPurchases = data.count();

        /**
         * 多少个不同的用户
         */
        long uniqueUsers = data.map(strings -> strings[0]).distinct().count();

        /**
         * 总的花费
         */
        double totalRevenue = data.mapToDouble(strings -> Double.parseDouble(strings[2])).sum();

        /**
         * 找到最畅销的产品
         * first we map the data to records of (product, 1) using a PairFunction
         * and the Tuple2 class.
         * then we call a reduceByKey operation with a Function2, which is essentially the sum function
         */
        List<Tuple2<String, Integer>> pairs = data.mapToPair(new PairFunction<String[], String, Integer>() {

            @Override
            public Tuple2<String, Integer> call(String[] strings) throws Exception {
                return new Tuple2(strings[1], 1);
            }
        }).reduceByKey((integer, integer2) -> integer + integer2).collect();


        // finally we sort the result. Note we need to create a Comparator function,
        // that reverses the sort order.
        Collections.sort(pairs, (o1, o2) -> -(o1._2() - o2._2()));
        String mostPopular = pairs.get(0)._1();
        int purchases = pairs.get(0)._2();

        // print everything out
        System.out.println("Total purchases: " + numPurchases);
        System.out.println("Unique users: " + uniqueUsers);
        System.out.println("Total revenue: " + totalRevenue);
        System.out.println(String.format("Most popular product: %s with %d purchases",
                mostPopular, purchases));

        jsc.stop();
    }
}
