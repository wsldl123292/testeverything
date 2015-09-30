import org.apache.hadoop.fs.Path
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.{Scan, Put}
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.mapred.TableOutputFormat
import org.apache.hadoop.hbase.mapreduce.TableInputFormat
import org.apache.hadoop.hbase.protobuf.ProtobufUtil
import org.apache.hadoop.hbase.util.{Base64, Bytes}
import org.apache.hadoop.mapred.JobConf
import org.apache.spark.SparkContext

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/9/10 17:39
 */
object SparkOnHBase {

    def convertScanToString(scan: Scan) = {
        val proto = ProtobufUtil.toScan(scan)
        Base64.encodeBytes(proto.toByteArray)
    }
    def main (args: Array[String]) {

        val sc = new SparkContext("local","SparkOnHbase")
        val conf = HBaseConfiguration.create()
        conf.addResource(new Path("/etc/hbase/conf/core-site.xml"))
        conf.addResource(new Path("/etc/hbase/conf/hbase-site.xml"))

        val jobConf = new JobConf(conf,this.getClass)
        jobConf.setOutputFormat(classOf[TableOutputFormat])
        jobConf.set(TableOutputFormat.OUTPUT_TABLE,"usertest")

        def convert(triple: (Int,String,Int)) = {
            val p = new Put(Bytes.toBytes(triple._1))
            p.addColumn(Bytes.toBytes("basic"),Bytes.toBytes("name"),Bytes.toBytes(triple._2))
            p.addColumn(Bytes.toBytes("basic"),Bytes.toBytes("age"),Bytes.toBytes(triple._3))
            (new ImmutableBytesWritable,p)
        }

        val rawData = List((1,"lilei",14),(2,"hanmei",18),(3,"someone",38))
        val localData = sc.parallelize(rawData).map(convert)

        localData.saveAsHadoopDataset(jobConf)

        conf.set(TableInputFormat.INPUT_TABLE, "user")

        val scan = new Scan()
        scan.setFilter(new SingleColumnValueFilter("basic".getBytes,"age".getBytes,CompareOp.GREATER_OR_EQUAL
        ,Bytes.toBytes(18)))

        conf.set(TableInputFormat.SCAN,convertScanToString(scan))

        val userRDD = sc.newAPIHadoopRDD(conf,classOf[TableInputFormat],classOf[org.apache.hadoop.hbase.io.ImmutableBytesWritable]
        ,classOf[org.apache.hadoop.hbase.client.Result])

        val count = userRDD.count()
        println("Users RDD Count:"+count)
        userRDD.cache()


        userRDD.foreach{case (_,result)=>
                val key = Bytes.toInt(result.getRow)
                val name = Bytes.toString(result.getValue("basic".getBytes,"name".getBytes))
                val age = Bytes.toInt(result.getValue("basic".getBytes,"age".getBytes))
                println("Row key:"+key+" Name:"+name+" Age:"+age)
        }
    }
}
