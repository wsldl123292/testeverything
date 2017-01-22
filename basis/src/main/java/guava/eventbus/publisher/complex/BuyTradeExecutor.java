package guava.eventbus.publisher.complex;

import com.google.common.eventbus.EventBus;
import guava.common.TradeAccount;
import guava.eventbus.events.BuyEvent;
import guava.eventbus.events.TradeAccountEvent;
import guava.eventbus.events.TradeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * User: Bill Bejeck
 * Date: 4/26/13
 * Time: 11:29 AM
 */
@Component
public class BuyTradeExecutor {

    private EventBus eventBus;

    @Autowired
    public BuyTradeExecutor(@Qualifier("buysEventBus")EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void executeBuyTrade(TradeAccount tradeAccount, double amount) {
        TradeAccountEvent tradeAccountEvent = processTrade(tradeAccount, amount, TradeType.BUY);
        eventBus.post(tradeAccountEvent);
    }

    private TradeAccountEvent processTrade(TradeAccount tradeAccount, double amount, TradeType tradeType) {
        Date executionTime = new Date();
        String message = String.format("Processed buy for %s of amount %n  @ %s", tradeAccount, amount, tradeType, executionTime);
        TradeAccountEvent tradeAccountEvent;
        tradeAccountEvent = new BuyEvent(tradeAccount, amount, executionTime);
        System.out.println(message);
        return tradeAccountEvent;
    }
}
