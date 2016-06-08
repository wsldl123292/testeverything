import com.saibowisdom.base.api.multidb.AbstractNode;
import com.saibowisdom.base.api.multidb.Dbservice;
import com.saibowisdom.base.api.multidb.ESConfig;
import com.saibowisdom.base.api.multidb.QueryCallback;
import com.saibowisdom.base.api.utils.SendEmailService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.Serializable;

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/10/9 15:41
 */
public class LuncherConsumer implements Serializable {

    public static void main(String[] args) throws InterruptedException {
        final LuncherConsumer luncher = new LuncherConsumer();
        luncher.start();
    }


    void start() {
        final String configLocation = "dubbo-consumer.xml";
        final ApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
        final Dbservice dbservice = (Dbservice) context.getBean("dbService");
        /*final SendEmailService emailService = (SendEmailService) context.getBean("sendEmail");*/
        final ESConfig esConfig = new ESConfig("elasticsearch", "192.168.1.211", 9300);
        /*final String sql = "select title " +
                "from es.cms_data_saibo$info " +
                "where  mediatype = 2 and (title ~ '(\"股份\" OR \"董事会\") AND (\"会议\")' or content ~ '(\"股份\" OR \"董事会\") AND (\"会议\")') and missing='psimhashrowkey'" +
                " order by createdate desc limit 0,10";*/

        final String sql = "select name from es.ttt2-all$ttt2 limit 10,20";

        final String sql1 = "select * from hbase.user limit 2";
////        final  String sql="select rowkey from es.kol_user_data$info where indextime>=0 and indextime<=0 and exist='topicrowkey'";

        String result = dbservice.selectRetJson(sql);
        System.err.println("result:" + result);

        //System.out.println(emailService.sendEmail("{\"subject\":\"预警\",\"to\":\"delin.liu@saibowisdom.com\",\"text\":\"json2object\"}"));
        /*dbservice.selectRetFile(sql, new QueryCallback() {

            @Override
            public void callback(String abstractNode, AbstractNode.EXESTATUS exestatus) {
                System.out.println(abstractNode + "  " + exestatus);
            }
        });*/

        //final ESConfig config = new ESConfig("elasticsearch","192.168.1.211",9300);
        //System.out.println("hello");
        //System.out.println(dbservice.selectRetJson(sql,config));

        /*int i = 1000;
        while (i > 0) {

            System.out.println(dbservice.selectRetJson(sql, esConfig));
            i--;
        }*/
        /*new Thread(new Runnable() {

            @Override
            public void run() {
                final String sql = "select highLight(content),highLight(title) " +
                        "from es.cms_data_saibo$info " +
                        "where mediatype = 2 and title like '音乐'  and missing='psimhashrowkey' " +
                        "order by createdate desc limit 0,10";
                System.out.println(dbservice.selectRetJson(sql));
            }
        }, "test1").start();
        new Thread(new Runnable() {

            @Override
            public void run() {
                final String sql = "select name from es.ttt$ttt";
                System.out.println(dbservice.selectRetJson(sql));
            }
        }, "test2").start();*/

    }
}
