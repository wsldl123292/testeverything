import com.typesafe.config.Config;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/9/16 16:16
 */
public class JavaCount implements Serializable{

    /*public static Object main(String[] args) throws Exception {
        return null;
    }*/

    public JavaPairRDD<String, Integer> runJob(JavaSparkContext sc, Config config) {
        String s = config.getString("input.string");
        final JavaPairRDD<String, Integer> lines = sc.parallelize(Arrays.asList(s.split(" "))).mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String s) {
                return new Tuple2<>(s, 1);
            }
        }).reduceByKey((i1, i2) -> i1 + i2);
        return lines;
    }
}
