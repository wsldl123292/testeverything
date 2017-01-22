package guava.eventbus.subscriber.simple;

import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import guava.eventbus.events.TradeAccountEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * User: Bill Bejeck
 * Date: 4/26/13
 * Time: 10:39 AM
 */
@Component
public class SimpleTradeAuditor {

    private List<TradeAccountEvent> tradeEvents = Lists.newArrayList();

    @Autowired
    public SimpleTradeAuditor(EventBus eventBus){
        checkNotNull(eventBus,"EventBus can't be null");
        eventBus.register(this);
    }

    @Subscribe
    public void auditTrade(TradeAccountEvent tradeAccountEvent){
        tradeEvents.add(tradeAccountEvent);
        System.out.println("Received trade "+tradeAccountEvent);
    }

    public List<TradeAccountEvent> getTradeEvents() {
        return tradeEvents;
    }
}
