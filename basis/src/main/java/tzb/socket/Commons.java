package tzb.socket;

import sun.reflect.Reflection;

import java.io.Closeable;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2017-05-16 22:41
 */
public class Commons {
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private final static SimpleDateFormat DATE_FORMAT_OBJECT = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);

    public final static String HELP_STR = "help";

    public final static String EXIT_STR = "exit";

    public final static int SEND_MESSAGE = 1;

    public final static String SEND_MESSAGE_STR = "sendMsg";

    public final static byte SEND_FILE = 2;

    public final static String SEND_FILE_STR = "sendFile";

    public final static byte SEND_B_FILE = 3;

    public final static String SEND_B_FILE_STR = "sendBFile";

    public final static byte GET_FILE = 4;

    public final static String GET_FILE_STR = "getFile";

    public final static String DEFAULT_MESSAGE_CHARSET = "utf-8";

    public final static String CHARSET_START = "charset=";

    public final static String SERVER_SAVE_BASE_PATH = "/javaA/upload/";

    public final static int DEFAULT_BUFFER_LENGTH = 8 * 1024;

    public final static String HELP_SHOW = "";

    public final static String ERROR_MESSAGE_FORMAT = "" + HELP_SHOW;

    public static void closeStream(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException ignored) {
        }
    }

    public static void logInfo(String message) {
        Class<?> clazz = Reflection.getCallerClass();
        String date = DATE_FORMAT_OBJECT.format(Calendar.getInstance().getTime());
        System.out.println(date + " [] INFO " + clazz.getName() + " - " + message);
    }
}
