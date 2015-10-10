import com.saibowisdom.base.api.multidb.AbstractNode;
import com.saibowisdom.base.api.multidb.Dbservice;
import com.saibowisdom.base.api.multidb.QueryCallback;
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
        final String sql = "select myweb,nickname from es.cms_data_internetdb$info where publishnum=0";

        dbservice.selectRetFile(sql, new QueryCallback() {

            @Override
            public void callback(String abstractNode, AbstractNode.EXESTATUS exestatus) {
                System.out.println(abstractNode + "  " + exestatus);
            }
        });

        System.out.println("hello");
        System.out.println(dbservice.selectRetJson(sql));
    }
}
