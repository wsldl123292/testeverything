import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.spark.api.java.JavaSparkContext;

import java.io.IOException;

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/9/10 15:14
 */
public class HbaseScan {
    public static void main(String[] args) {

        if (args.length == 0) {
            System.out
                    .println("JavaHBaseBulkGetExample  {master} {tableName}");
        }

        String master = args[0];
        String tableName = args[1];

        JavaSparkContext jsc = new JavaSparkContext(master,
                "SparkHBase");

        Configuration conf = HBaseConfiguration.create();
        conf.addResource(new Path("/etc/hbase/conf/core-site.xml"));
        conf.addResource(new Path("/etc/hbase/conf/hbase-site.xml"));
        Connection connection = null;
        TableName userTable = null;
        try {
            connection = ConnectionFactory.createConnection(conf);
            //本例将操作的表名
            userTable = TableName.valueOf(tableName);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            //获取 user 表
            Table table = connection.getTable(userTable);

            try {
                //扫描数据
                Scan s = new Scan();
                ResultScanner scanner = table.getScanner(s);

                try {
                    for (Result r : scanner) {
                        System.out.println("Found row: " + r);
                    }
                } finally {
                    //确保scanner关闭
                    scanner.close();
                }


            } finally {
                if (table != null) table.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            jsc.stop();
        }
    }
}
