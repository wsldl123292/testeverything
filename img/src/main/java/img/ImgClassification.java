package img;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.google.common.io.Files;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2017-09-03 15:52
 */
public class ImgClassification {

    private static String newFilePath = null;

    private static final Long MAX_SIZE = 1048576L;

    private static LinkedBlockingQueue queue = new LinkedBlockingQueue(5000);

    private static ExecutorService executor = Executors.newFixedThreadPool(3);

    private static CountDownLatch latch = new CountDownLatch(3);

    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        if (args == null) {
            throw new Exception("请输入参数");
        }
        if (args[0] == null) {
            throw new Exception("请输入源地址");
        }
        if (args[1] == null) {
            throw new Exception("请输入目标地址");
        }
        long start = System.currentTimeMillis();
        newFilePath = args[1];
        File parent = new File(args[0]);

        toQueue(parent);

        for (int i = 0; i < 3; i++) {
            executor.submit(new imageTask());
        }
        latch.await();
        System.out.println("执行结束,共完成:" + count + ",共耗时:" + (System.currentTimeMillis() - start) / 1000);
        executor.shutdown();

    }

    @SuppressWarnings("unchecked")
    private static void toQueue(File root) throws Exception {
        if (root.isDirectory()) {
            File[] files = root.listFiles();
            if (files != null) {
                for (File file : files) {
                    toQueue(file);
                }
            }

        } else {
            if (root.getName().contains(".jpg") && root.length() > MAX_SIZE) {
                queue.add(root);
            }
        }
    }


    private static class imageTask implements Runnable {

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            while (!queue.isEmpty()) {
                File image = null;
                image = (File) queue.poll();
                if (image != null) {
                    try {
                        System.out.println("当前执行第" + count + "个");
                        printImageTags(image);
                        count.addAndGet(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
            latch.countDown();
        }
    }

    private static void moveFile(String newFilePath, Location location, File file, String createDate) throws Exception {
        if (createDate == null) {
            createDate = "未知时间";
        }
        if (location != null) {
            File targetFile = new File(newFilePath + "/" + location.getProvince() + "/" + location.getCity() + "/" + location.getDistrict() + "/" + createDate + "/" + file.getName());
            File newFile = new File(newFilePath + "/" + location.getProvince() + "/" + location.getCity() + "/" + location.getDistrict() + "/" + createDate);
            createFileAndMove(file, targetFile, newFile);
        } else {
            File targetFile = new File(newFilePath + "/" + createDate + "/" + file.getName());
            File newFile = new File(newFilePath + "/" + createDate);
            createFileAndMove(file, targetFile, newFile);
        }
    }

    private static void createFileAndMove(File file, File targetFile, File newFile) throws Exception {
        if (!newFile.exists()) {
            boolean isTrue = newFile.mkdirs();
            if (!isTrue) {
                throw new Exception("创建文件夹失败!");
            }
            Files.move(file, targetFile);
        } else {
            Files.move(file, targetFile);
        }
    }

    /**
     * 读取照片里面的信息
     */
    private static void printImageTags(File file) throws Exception {
        Metadata metadata = ImageMetadataReader.readMetadata(file);
        String createDate = null;
        String lat = null;
        String lon = null;
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                String tagName = tag.getTagName();  //标签名
                String desc = tag.getDescription(); //标签信息
                switch (tagName) {
                    case "Date/Time Original":
                        createDate = desc.split(" ")[0].replace(":", "-");
                        break;
                    case "GPS Latitude":
                        lat = desc;
                        break;
                    case "GPS Longitude":
                        lon = desc;
                        break;
                }
            }
        }
        moveFile(newFilePath, getposition(pointToLatlong(lat), pointToLatlong(lon)), file, createDate);
    }


    /**
     * 经纬度格式  转换为  度分秒格式 ,如果需要的话可以调用该方法进行转换
     *
     * @param point 坐标点
     * @return
     */
    private static String pointToLatlong(String point) {
        if (point == null) {
            return null;
        }
        Double du = Double.parseDouble(point.substring(0, point.indexOf("°")).trim());
        Double fen = Double.parseDouble(point.substring(point.indexOf("°") + 1, point.indexOf("'")).trim());
        Double miao = Double.parseDouble(point.substring(point.indexOf("'") + 1, point.indexOf("\"")).trim());
        Double duStr = du + fen / 60 + miao / 60 / 60;
        return duStr.toString();
    }


    private static Location getposition(String latitude, String longitude) throws MalformedURLException {
        if (latitude == null || longitude == null) {
            return null;
        }
        BufferedReader in;
        URL tirc = new URL("http://api.map.baidu.com/geocoder?location=" + latitude + "," + longitude + "&output=json&key=" + "E4805d16520de693a3fe707cdc962045");
        try {
            in = new BufferedReader(new InputStreamReader(tirc.openStream(), "UTF-8"));
            String res;
            StringBuilder sb = new StringBuilder("");
            while ((res = in.readLine()) != null) {
                sb.append(res.trim());
            }
            JSONObject object = JSON.parseObject(sb.toString());
            if (object.get("status") != null && Objects.equals(object.getString("status"), "OK")) {
                JSONObject result = JSON.parseObject(object.getString("result"));
                return JSON.parseObject(result.getString("addressComponent"), Location.class);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    static class Location {
        private String city;
        private String district;
        private String province;


        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        @Override
        public String toString() {
            return "Location{" +
                    "city='" + city + '\'' +
                    ", district='" + district + '\'' +
                    ", province='" + province + '\'' +
                    '}';
        }
    }

}