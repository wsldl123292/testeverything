import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by LDL on 2015/11/4.
 */
public class HDFSTest {

    public static void main(String[] args) {
        System.setProperty("hadoop.home.dir", "F:\\data\\hadoop-common-2.2.0-bin-master");
        try {
            //testExists("/data/hdfs/dt="+new SimpleDateFormat("yyyyMMdd").format(new Date()));
            //mkdir("/data/hdfs/dt="+new SimpleDateFormat("yyyyMMdd").format(new Date()));
            writeToHDFS("/data/hdfs/dt="+new SimpleDateFormat("yyyyMMdd").format(new Date())+"/data.txt","hello1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void mkdir(String path) throws Exception {
        //System.setProperty("hadoop.home.dir", "F:\\data\\hadoop-common-2.2.0-bin-master");
        final Configuration conf = new Configuration();
        conf.addResource(new Path("F:\\core-site.xml"));
        final FileSystem fs = FileSystem.get(conf);
        final Path srcPath = new Path(path);
        final boolean isok = fs.mkdirs(srcPath);
        if (isok) {
            System.out.println("create dir ok!");
        } else {
            System.out.println("create dir failure");
        }
        fs.close();
    }

    // 查看HDFS文件是否存在

    public static void testExists(String hdfsPath) throws Exception {

        Configuration conf = new Configuration();
        conf.addResource(new Path("F:\\core-site.xml"));
        FileSystem hdfs = FileSystem.get(conf);
        Path dst = new Path(hdfsPath);

        boolean ok = hdfs.exists(dst);
        System.out.println(ok ? "文件存在" : "文件不存在");
    }

    public static void writeToHDFS(String file, String words) throws IOException, URISyntaxException {

        final Configuration conf = new Configuration();
        conf.addResource(new Path("F:\\core-site.xml"));
        final FileSystem fs = FileSystem.get(URI.create(file), conf);
        final Path path = new Path(file);
        final FSDataOutputStream out = fs.create(path);   //创建文件

        out.write(words.getBytes("UTF-8"));

        out.close();
    }
}


