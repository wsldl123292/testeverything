package guava.eventbus;

import com.google.common.eventbus.EventBus;
import guava.eventbus.events.BuyEvent;
import guava.eventbus.events.SellEvent;
import guava.eventbus.events.TradeAccountEvent;

/**
 * User: Bill Bejeck
 * Date: 4/28/13
 * Time: 8:01 PM
 */
public abstract class EventBusTestBase {


    protected TradeAccountEvent.Builder getTradeAccountEventBuilder(){
        return new TradeAccountEvent.Builder();
    }

    protected BuyEvent.Builder buyEventBuilder(){
        return new BuyEvent.Builder();
    }

    protected SellEvent.Builder sellEventBuilder(){
        return new SellEvent.Builder();
    }

    protected EventBus getEventBus() {
        return new EventBus();
    }



}
