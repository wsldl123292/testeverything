package guava.eventbus.subscriber;

import com.google.common.eventbus.EventBus;
import guava.eventbus.EventBusTestBase;
import guava.eventbus.subscriber.complex.TradeBuyAuditor;
import guava.eventbus.subscriber.complex.TradeSellAuditor;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: Bill Bejeck
 * Date: 4/28/13
 * Time: 8:49 PM
 */
public class TradeBuySellAuditorTest extends EventBusTestBase {

    private TradeBuyAuditor buyAuditor;
    private TradeSellAuditor sellAuditor;
    private EventBus eventBus;

    @Before
    public void setUp(){
        eventBus = getEventBus();
        buyAuditor = new TradeBuyAuditor(eventBus);
        sellAuditor = new TradeSellAuditor(eventBus);
    }

    @Test
    public void sellOnlyTest(){
        eventBus.post(sellEventBuilder().build());
        assertThat(sellAuditor.getSellEvents().size(),is(1));
        assertThat(buyAuditor.getBuyEvents().isEmpty(),is(true));
    }

    @Test
    public void buyOnlyTest(){
        eventBus.post(buyEventBuilder().build());
        assertThat(sellAuditor.getSellEvents().isEmpty(),is(true));
        assertThat(buyAuditor.getBuyEvents().size(),is(1));
    }
}
