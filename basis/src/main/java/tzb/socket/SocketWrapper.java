package tzb.socket;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

import static tzb.socket.Commons.closeStream;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2017-05-16 22:14
 */
public class SocketWrapper {
    private final static int PAGE_SIZE = 1024 * 1024;
    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public SocketWrapper(Socket socket) throws IOException {
        this.socket = socket;
        processStreams();
    }

    public SocketWrapper(String host, int port) throws IOException {
        this.socket = new Socket();
        this.socket.connect(new InetSocketAddress(host, port), 1500);
        this.socket.setKeepAlive(true);
        this.socket.setTcpNoDelay(true);
        processStreams();

    }

    private void processStreams() throws IOException {
        this.inputStream = new DataInputStream(socket.getInputStream());
        this.outputStream = new DataOutputStream(socket.getOutputStream());
    }

    public void write(byte b) throws IOException {
        this.outputStream.write(b);
    }

    public void write(short s) throws IOException {
        this.outputStream.writeShort(s);
    }

    public void write(int i) throws IOException {
        this.outputStream.writeInt(i);
    }

    public void write(long l) throws IOException {
        this.outputStream.writeLong(l);
    }

    public void write(byte[] bytes) throws IOException {
        this.outputStream.write(bytes);
    }

    public void write(byte[] bytes, int length) throws IOException {
        this.outputStream.write(bytes, 0, length);
    }

    public void write(String value, String charset) throws IOException {
        if (value != null) {
            write(value.getBytes(charset));
        }
    }

    public byte readByte() throws IOException {
        return this.inputStream.readByte();
    }

    public short readShort() throws IOException {
        return this.inputStream.readShort();
    }

    public int readInt() throws IOException {
        return this.inputStream.readInt();
    }

    public long readLong() throws IOException {
        return this.inputStream.readLong();
    }

    public void readFull(byte[] bytes) throws IOException {
        this.inputStream.readFully(bytes);
    }

    public int read(byte[] bytes) throws IOException {
        return this.inputStream.read(bytes);
    }

    public String read(int length, String charset) throws IOException {
        byte[] bytes = new byte[length];
        read(bytes);
        return new String(bytes, charset);
    }

    public void writeFile(String path) throws IOException {
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);
        try {

            long fileLength = file.length();
            if (fileLength > PAGE_SIZE) {
                byte[] bytes = new byte[PAGE_SIZE];
                int allLength = 0;
                int length = fileInputStream.read(bytes);
                while (length > 0) {
                    allLength += length;
                    this.write(bytes, length);
                    length = fileInputStream.read(bytes);
                    System.out.print(".");
                }
                System.out.println("实际发送文件长度为:" + allLength);
            } else {
                byte[] bytes = new byte[(int) fileLength];
                fileInputStream.read(bytes);
                this.write(bytes);
            }
        } finally {
            closeStream(inputStream);
        }
    }
}
