package eventbus.publisher.simple;

import com.google.common.eventbus.EventBus;
import common.TradeAccount;
import eventbus.events.TradeAccountEvent;
import eventbus.events.TradeType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * User: Bill Bejeck
 * Date: 4/26/13
 * Time: 11:29 AM
 */
public class SimpleTradeExecutor {

    private EventBus eventBus;

    @Autowired
    public SimpleTradeExecutor(EventBus eventBus) {
        this.eventBus = checkNotNull(eventBus, "EventBus can't be null");
    }

    public void executeTrade(TradeAccount tradeAccount, double amount, TradeType tradeType) {
        TradeAccountEvent tradeAccountEvent = processTrade(tradeAccount, amount, tradeType);
        eventBus.post(tradeAccountEvent);
    }

    protected TradeAccountEvent processTrade(TradeAccount tradeAccount, double amount, TradeType tradeType) {
        Date executionTime = new Date();
        String message = String.format("Processed trade for %s of amount %n type %s @ %s", tradeAccount, amount, tradeType, executionTime);
        TradeAccountEvent tradeAccountEvent = new TradeAccountEvent(tradeAccount, amount, executionTime, tradeType);
        System.out.println(message);
        return tradeAccountEvent;
    }
}
