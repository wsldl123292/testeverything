package eventbus;

import com.google.common.eventbus.EventBus;
import eventbus.events.BuyEvent;
import eventbus.events.SellEvent;
import eventbus.events.TradeAccountEvent;

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
