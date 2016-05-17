import org.joda.time.LocalDate;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.zip.DataFormatException;

/**
 * 功 能:
 * 创建人: LDL
 * 时 间:  2015/12/16 10:26
 */
public class JodaTest {

    LocalDate localDate = LocalDate.now();
    private static String dateTrans(String dt) {
        /** 数字匹配 */
        final String regN = "^\\d+$";
        /** 英文和数字 */
        final String regNE = ".*\\p{Alpha}.*";
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final SimpleDateFormat dateFormat0 = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        final SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = null;
        try {
            /** 时间戳格式 */
            if (dt.matches(regN)) {
                if (dt.length() == 10) {
                    dt = dt + "000";
                    dt = dateFormat.format(new Date(Long.parseLong(dt)));
                    date = dt;
                } else if (dt.length() == 8) {
                    //yyyyMMdd格式日期转换为yyyyMMddHHmmss格式
                    dt = dt + "000000";
                    dt = dateFormat.format(dateFormat1.parse(dt));
                    date = dt;
                } else if (dt.length() == 14) {
                    //yyyyMMddHHmmss格式
                    dt = dateFormat.format(dateFormat1.parse(dt));
                    date = dt;
                } else if (dt.length() == 13) {
                    //yyyyMMddHHmmss格式
                    dt = dateFormat.format(new Date(Long.parseLong(dt)));
                    date = dt;
                }
                //return dt;
            } else if (dt.matches(regNE)) {
                String monthStr = "MMM";
                if (dt.substring(3, 4).equals(".")) {
                    //月份简写,Jan.,Feb.
                    //Dec. 15, 2015 15:47
                    monthStr = "MMM.";
                }
                if (dt.substring(6, 7).equals(".")) {
                    //15 Dec., 2015 15:47
                    monthStr = "MMM.";
                }

                if (dt.contains("+")) {
                    dt = dt.substring(0, dt.indexOf("+"));
                }
                //Dec. 15, 2015 15:47  December 16, 2015 Dec 15, 2015 15:47
                final SimpleDateFormat sdf0 = new SimpleDateFormat(monthStr + " d,yyyy", Locale.ENGLISH);
                final SimpleDateFormat sdf1 = new SimpleDateFormat(monthStr + " d,yyyy HH:mm", Locale.ENGLISH);
                final SimpleDateFormat sdf2 = new SimpleDateFormat(monthStr + " d,yyyy HH:mm:ss", Locale.ENGLISH);

                //16 December 2015   16 Dec 2015    16 Dec. 2015
                final SimpleDateFormat sdf3 = new SimpleDateFormat("d " + monthStr + ", yyyy", Locale.ENGLISH);
                System.out.println(sdf3.parse(dt));
                final SimpleDateFormat sdf4 = new SimpleDateFormat("d " + monthStr + ", yyyy HH:mm", Locale.ENGLISH);
                final SimpleDateFormat sdf5 = new SimpleDateFormat("d " + monthStr + ", yyyy HH:mm:ss", Locale.ENGLISH);

                //Tuesday|Tue 15 December|Dec 2015
                final SimpleDateFormat sdf6 = new SimpleDateFormat("EEE d " + monthStr + " yyyy", Locale.ENGLISH);
                final SimpleDateFormat sdf7 = new SimpleDateFormat("EEE d " + monthStr + " yyyy HH:mm", Locale.ENGLISH);
                final SimpleDateFormat sdf8 = new SimpleDateFormat("EEE d " + monthStr + " yyyy HH:mm:ss", Locale.ENGLISH);


                //11:24 AM - 16 Dec 2015
                final SimpleDateFormat sdf9 = new SimpleDateFormat("K:m:s a - d MMM yyyy", Locale.ENGLISH);
                final SimpleDateFormat sdf10 = new SimpleDateFormat("K:m:s a - d MMM yyyy", Locale.ENGLISH);

                //2015-12-16T03:23:17.523Z
                final SimpleDateFormat sdf11 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.ENGLISH);

                //2015-12-15T20:38:45+0000
                final SimpleDateFormat sdf12 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                final ArrayList<SimpleDateFormat> dateFormats = new ArrayList<>();
                dateFormats.add(sdf12);
                dateFormats.add(sdf11);
                dateFormats.add(sdf10);
                dateFormats.add(sdf9);
                dateFormats.add(sdf8);
                dateFormats.add(sdf7);
                dateFormats.add(sdf6);
                dateFormats.add(sdf5);
                dateFormats.add(sdf4);
                dateFormats.add(sdf3);
                dateFormats.add(sdf2);
                dateFormats.add(sdf1);
                dateFormats.add(sdf0);
                Date parseDate = null;
                for (SimpleDateFormat dformat : dateFormats) {
                    boolean result = true;
                    try {
                        parseDate = dformat.parse(dt);
                    } catch (Exception e) {
                        result = false;
                        parseDate = null;
                    }
                    if (result) {
                        break;
                    }
                }
                dt = dateFormat.format(parseDate);
                date = dt;
            } else {
                dt = dt.replace("/", "-").replace("年", "-").replace("月", "-").replace("日", "").replace("时", ":").replace("分", ":").replace("秒", "").trim();
                if (dt.length() == 10) {
                    //yyyy-MM-dd
                    dt = dateFormat.format(dateFormat0.parse(dt));
                    date = dt;
                } else if (dt.length() == 19) {
                    date = dt;
                    return date;
                } else if (dt.length() == 16) {
                    //yyyy-MM-dd HH:mm
                    dt = dateFormat.format(dateFormat2.parse(dt));
                    date = dt;
                }
            }
        } catch (Exception e) {
            date = null;
        }
        return date;
    }

    public static void main(String[] args) throws ParseException {
        final String regNE = ".*\\p{Alpha}.*";
        final SimpleDateFormat sdf1 = new SimpleDateFormat("d MMM yyyy",Locale.ENGLISH);
        String date = "16 December 2015";
        //String date = "December 16, 2015";
        System.out.println(sdf1.parse(date));
        //String date1 = "December 15, 2015";
        System.out.println(date.matches(regNE));
        System.out.println(dateTrans(date));
    }
}
