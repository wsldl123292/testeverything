package guava.eventbus.publisher.complex;

import com.google.common.eventbus.EventBus;
import guava.common.TradeAccount;
import guava.eventbus.events.SellEvent;
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
public class SellTradeExecutor {

    private EventBus eventBus;

    @Autowired
    public SellTradeExecutor(@Qualifier("salesEventBus")EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void executeSaleTrade(TradeAccount tradeAccount, double amount) {
        TradeAccountEvent tradeAccountEvent = processTrade(tradeAccount, amount, TradeType.SELL);
        eventBus.post(tradeAccountEvent);
    }

    private TradeAccountEvent processTrade(TradeAccount tradeAccount, double amount, TradeType tradeType) {
        Date executionTime = new Date();
        String message = String.format("Processed sale for %s of amount %n  @ %s", tradeAccount, amount, tradeType, executionTime);
        TradeAccountEvent tradeAccountEvent;
        tradeAccountEvent = new SellEvent(tradeAccount, amount, executionTime);
        System.out.println(message);
        return tradeAccountEvent;
    }
}
