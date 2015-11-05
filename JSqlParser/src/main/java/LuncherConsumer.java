import com.saibowisdom.base.api.multidb.Dbservice;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.Serializable;

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/10/9 15:41
 */
public class LuncherConsumer implements Serializable{

    public static void main(String[] args) throws InterruptedException {
        final LuncherConsumer luncher = new LuncherConsumer();
        luncher.start();
    }


    void start() {
        final String configLocation = "dubbo-consumer.xml";
        final ApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
        final Dbservice dbservice = (Dbservice) context.getBean("dbService");
        /*final String sql = "select title " +
                "from es.cms_data_saibo$info " +
                "where  mediatype = 2 and (title ~ '(\"股份\" OR \"董事会\") AND (\"会议\")' or content ~ '(\"股份\" OR \"董事会\") AND (\"会议\")') and missing='psimhashrowkey'" +
                " order by createdate desc limit 0,10";*/

        final String sql = "select test(title) " +
                "from es.cms_data_saibo$info limit 0,10";

        /*dbservice.selectRetFile(sql, new QueryCallback() {

            @Override
            public void callback(String abstractNode, AbstractNode.EXESTATUS exestatus) {
                System.out.println(abstractNode + "  " + exestatus);
            }
        });*/
        //System.out.println("hello");
        System.out.println(dbservice.selectRetJson(sql));

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
                final String sql = "select content,title from es.cms_data_saibo$info " +
                        "where mediatype = 2 and title like '音乐'  and missing='psimhashrowkey' " +
                        "order by createdate desc limit 0,10";
                System.out.println(dbservice.selectRetJson(sql,"elasticsearch","192.168.1.211",9300));
            }
        }, "test2").start();*/

    }
}
