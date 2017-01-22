package guava.eventbus.config;

import guava.common.TradeAccount;
import guava.eventbus.events.TradeAccountEvent;
import guava.eventbus.events.TradeType;
import guava.eventbus.publisher.simple.SimpleTradeExecutor;
import guava.eventbus.subscriber.simple.SimpleTradeAuditor;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: Bill Bejeck
 * Date: 4/26/13
 * Time: 4:31 PM
 */
public class SimpleEventBusSpringJavaConfigTest {

    SimpleTradeExecutor tradeExecutor;
    SimpleTradeAuditor tradeAuditor;

    @Before
    public void setUp(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SimpleEventBusConfig.class);
         tradeExecutor = ctx.getBean(SimpleTradeExecutor.class);
         tradeAuditor = ctx.getBean(SimpleTradeAuditor.class);
    }

    @Test
    public void testSendMessage(){
        TradeAccount tradeAccount = new TradeAccount.Builder().build();
        tradeExecutor.executeTrade(tradeAccount,5000.65, TradeType.BUY);
        List<TradeAccountEvent> tradeAccountEvents = tradeAuditor.getTradeEvents();
        assertThat(tradeAccountEvents.get(0).getTradeAccount(),is(tradeAccount));
    }
}
