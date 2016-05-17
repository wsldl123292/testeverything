import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.search.SearchHit;
import org.lionsoul.jcseg.ASegment;
import org.lionsoul.jcseg.core.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功 能:
 * 创建人: LDL
 * 时 间:  2016/2/26 11:10
 */
public class EsToFile {

    private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
    private static final String regEx_space = "\\s*|\t|\r|\n";//定义空格回车换行符

    public static String delHTMLTag(String htmlStr) {
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); // 过滤script标签

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); // 过滤style标签

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签

        Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
        Matcher m_space = p_space.matcher(htmlStr);
        htmlStr = m_space.replaceAll(""); // 过滤空格回车标签
        return htmlStr.trim(); // 返回文本字符串
    }


    public static void main(String[] args) throws IOException, JcsegException {
        final Settings settings = ImmutableSettings.settingsBuilder()
                .put("client.transport.sniff", true).put("cluster.name", "es").build();

        final TransportClient client = new TransportClient(settings).addTransportAddresses(new InetSocketTransportAddress("192.168.1.212", 9300));

        final SearchRequestBuilder srb1 = client
                .prepareSearch("cms_data_huishan")
                .setTypes("info")
                .setSize(5200);
                /*.setQuery(QueryBuilders.boolQuery().must(QueryBuilders.rangeQuery("createdate").gte("1443776524000"))
                        .must(QueryBuilders.rangeQuery("createdate").lte("1444640524000")));*/
        /*System.out.println(srb1);
        System.out.println(srb1.execute().actionGet());*/
        final SearchResponse searchResponse = srb1.execute().actionGet();
        final SearchHit[] hits = searchResponse.getHits().getHits();
        /*FileWriter f = new FileWriter("F:\\data.txt");
        for (SearchHit hit : hits) {
            final List<Term> termList = ToAnalysis.parse(delHTMLTag(hit.getSource().get("content").toString()));
            for (Term term : termList) {
                f.write(term.getName() + " ");
            }
            f.write("\n");
        }
        f.close();*/
        System.out.println(ToAnalysis.parse(delHTMLTag(hits[0].getSource().get("content").toString())));


        JcsegTaskConfig config = new JcsegTaskConfig();
        ADictionary dic = DictionaryFactory
                .createDefaultDictionary(config);
//依据给定的ADictionary和JcsegTaskConfig来创建ISegment
//通常使用SegmentFactory#createJcseg来创建ISegment对象
//将config和dic组成一个Object数组给SegmentFactory.createJcseg方法
//JcsegTaskConfig.COMPLEX_MODE表示创建ComplexSeg复杂ISegment分词对象
//JcsegTaskConfig.SIMPLE_MODE表示创建SimpleSeg简易Isegmengt分词对象.
        ASegment seg = (ASegment) SegmentFactory
                .createJcseg(JcsegTaskConfig.COMPLEX_MODE,
                        new Object[]{config, dic});

        seg.reset(new StringReader(delHTMLTag(hits[0].getSource().get("content").toString())));
//获取分词结果
        IWord word = null;
        while ( (word = seg.next()) != null ) {
            System.out.println(word.getValue());
        }
    }

}
