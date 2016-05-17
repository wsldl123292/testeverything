import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class EsHttpQueryUtils {

    public static void main(String[] args) throws IOException {
        //363455
        /*final Map<String, Object> hashmap = searchEsByESql("select domain,url from es.original_data$original_data where missing='content' order by domain limit 300000, 400000");
        final Object obj = hashmap.get("list");
        final JSONArray jsonArray = JSON.parseArray(obj.toString());
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < jsonArray.size(); i++) {
            Map<String, Object> o = (Map<String, Object>) JSON.parse(jsonArray.getString(i));
            if (o.get("domain") != null) {
                if (o.get("domain").equals("nytimes.com") || o.get("domain").equals("blogs.nytimes.com") ||
                        o.get("domain").equals("www.nytimes.com") || o.get("domain").equals("www.theguardian.com") ||
                        o.get("domain").equals("english.chosun.com")) {
                    continue;
                }
            }
            stringBuilder.append(o.get("domain") != null ? o.get("domain") : "").append("            ")
                    .append(o.get("url") != null ? o.get("url") : "").append("\n");
        }

        Files.write(stringBuilder.toString(), new File("result.txt"), Charsets.UTF_8);*/
        System.out.println(searchEsByESql("select mediatype " +
                "from es.cms_data_saibo$info where " +
                "mediatype=4 or mediatype=6 or mediatype=7 "));
    }

    /**
     * 通过接口查询sql
     *
     * @param sql sql
     * @throws UnsupportedEncodingException 异常
     */
    public static Map<String, Object> searchEsByESql(String sql) throws UnsupportedEncodingException {
        final HttpClient httpclient = new DefaultHttpClient();
        final Map<String, Object> map = new HashMap<>();
        final String url = "http://dubboservice.saibowisdom.com/services/search/selectjson";
        final HttpPost pmethod = new HttpPost(url); // 设置响应头信息
        pmethod.addHeader("Connection", "keep-alive");
        pmethod.addHeader("Accept", "*/*");
        pmethod.addHeader("Content-Type", "application/json; charset=UTF-8");
        pmethod.addHeader("X-Requested-With", "XMLHttpRequest");
        pmethod.addHeader("Cache-Control", "max-age=0");
        pmethod.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");

        final HttpParams httpParameters = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParameters, 1000 * 120);//设置超时时间
        HttpConnectionParams.setSoTimeout(httpParameters, 1000 * 120);
        pmethod.setParams(httpParameters);

        final StringEntity se = new StringEntity(sql, HTTP.UTF_8);
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        pmethod.setEntity(se);
        final HttpResponse res;
        String jsonStr = "";
        try {
            //res = HttpUtil.getHttpClient().execute(pmethod);
            res = httpclient.execute(pmethod);
            if (res != null) {
                final HttpEntity entity = res.getEntity();
                jsonStr = EntityUtils.toString(entity, "utf-8");
            }
        } catch (Exception e) {
            map.put("success", false);
            map.put("info", e.getMessage());
            e.printStackTrace();
        } finally {
//			System.out.println("release connection");
            pmethod.releaseConnection();
        }
        if (null != jsonStr && !"".equals(jsonStr)) {
            final JSONObject object = JSON.parseObject(jsonStr);
            if (null != object.get("error")) {
                map.put("success", false);
                map.put("info", object.get("error").toString());
            } else {
                map.put("success", true);
                map.put("list", object.get("data"));
                map.put("total", object.get("total"));
                map.put("qTime", object.get("took"));
            }

        }
        return map;

    }

}
