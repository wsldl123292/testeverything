import org.lionsoul.jcseg.tokenizer.Dictionary;
import org.lionsoul.jcseg.tokenizer.core.*;

import java.io.IOException;
import java.io.StringReader;

/**
 * @author liudelin
 * @date 2018/3/20 10:15
 */
public class Jcseg {

    public static void main(String[] args) throws IOException, JcsegException {
        JcsegTaskConfig config = new JcsegTaskConfig("C:\\Users\\Administrator\\Desktop\\jcsegc.properties");
        config.setLexiconPath(new String[]{
                "C:\\Users\\Administrator\\Desktop\\lex"
                //add more here
        });
//创建默认单例词库实现，并且按照config配置加载词库
        ADictionary dic = DictionaryFactory.createSingletonDictionary(config);
        dic.loadClassPath();
//依据给定的ADictionary和JcsegTaskConfig来创建ISegment
//为了Api往后兼容，建议使用SegmentFactory来创建ISegment对象
        ISegment seg = SegmentFactory.createJcseg(
                JcsegTaskConfig.NLP_MODE,
                new Object[]{config, dic}
        );

//备注：以下代码可以反复调用，seg为非线程安全

//设置要被分词的文本
        String str = "2015年10月19日浦发银行信用卡中心发放的贷记卡（人民币账户）销户。截至2018年2月，信用额度46000，已使用额度2664。";
        seg.reset(new StringReader(str));

        //获取分词结果
        IWord word = null;
        while ((word = seg.next()) != null) {
                System.out.println(word.getValue());
        }
    }
}
