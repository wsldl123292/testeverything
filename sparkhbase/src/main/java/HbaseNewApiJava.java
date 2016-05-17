import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.spark.api.java.JavaSparkContext;

import java.io.IOException;

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/9/10 14:52
 */
public class HbaseNewApiJava {
    public static void main(String[] args) {
        /*if (args.length == 0) {
            System.out
                    .println("JavaHBaseBulkGetExample  {master} {tableName}");
        }

        JavaSparkContext jsc = new JavaSparkContext("yarn-client",
                "SparkHBase");*/

        Configuration conf = HBaseConfiguration.create();
        conf.addResource(new Path("/etc/hbase/conf/core-site.xml"));
        conf.addResource(new Path("/etc/hbase/conf/hbase-site.xml"));
        Connection connection = null;
        Admin admin;
        TableName userTable = null;
        HTableDescriptor tableDescr;
        try {
            connection = ConnectionFactory.createConnection(conf);
            admin = connection.getAdmin();
            //本例将操作的表名
            userTable = TableName.valueOf("usertest");

            //创建 user 表
            tableDescr = new HTableDescriptor(userTable);
            tableDescr.addFamily(new HColumnDescriptor("basic".getBytes()));
            System.out.println("Creating table `usertest`. ");
            if (admin.tableExists(userTable)) {
                admin.disableTable(userTable);
                admin.deleteTable(userTable);
            }
            admin.createTable(tableDescr);
            System.out.println("Done!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            //获取 user 表
            Table table = connection.getTable(userTable);

            try {
                //准备插入一条 key 为 id001 的数据
                Put p = new Put("id001".getBytes());
                //为put操作指定 column 和 value （以前的 put.add 方法被弃用了）
                p.addColumn("basic".getBytes(), "name".getBytes(), "wuchong".getBytes());
                //提交
                table.put(p);

                //查询某条数据
                Get g = new Get("id001".getBytes());
                Result result = table.get(g);
                String value = Bytes.toString(result.getValue("basic".getBytes(), "name".getBytes()));
                System.out.println("GET id001 :" + value);

                //扫描数据
                Scan s = new Scan();
                s.addColumn("basic".getBytes(), "name".getBytes());
                ResultScanner scanner = table.getScanner(s);

                try {
                    for (Result r : scanner) {
                        System.out.println("Found row: " + r);
                        System.out.println("Found value: " + Bytes.toString(r.getValue("basic".getBytes(), "name".getBytes())));
                    }
                } finally {
                    //确保scanner关闭
                    scanner.close();
                }

                //删除某条数据,操作方式与 Put 类似
                Delete d = new Delete("id001".getBytes());
                d.addColumn("basic".getBytes(), "name".getBytes());
                table.delete(d);

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
        }
    }
}
