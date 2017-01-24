package nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @描述
 * @作者 liudelin
 * @日期 2017/1/22 14:23
 */
public class FileChannelDemo {

    public static void main(String[] args) throws IOException {
        RandomAccessFile accessFile = new RandomAccessFile("C:\\Users\\Administrator\\Desktop\\bill_id.txt", "rw");
        FileChannel fileChannel = accessFile.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(6);

        int bytesRead = fileChannel.read(byteBuffer);
        while (bytesRead != -1) {
            System.out.println(fileChannel.position());
            byteBuffer.flip();
            byteBuffer.clear();
            bytesRead = fileChannel.read(byteBuffer);
        }

    }
}
