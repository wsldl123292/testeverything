package guava.eventbus.publisher;

import com.google.common.eventbus.EventBus;
import guava.common.TradeAccount;
import guava.eventbus.events.BuyEvent;
import guava.eventbus.events.SellEvent;
import guava.eventbus.events.TradeAccountEvent;
import guava.eventbus.events.TradeType;

import java.util.Date;

/**
 * User: Bill Bejeck
 * Date: 4/26/13
 * Time: 11:29 AM
 */
public class BuySellTradeExecutor {

    private EventBus eventBus;

    public BuySellTradeExecutor(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void executeTrade(TradeAccount tradeAccount, double amount, TradeType tradeType) {
        TradeAccountEvent tradeAccountEvent = processTrade(tradeAccount, amount, tradeType);
        eventBus.post(tradeAccountEvent);
    }

    private TradeAccountEvent processTrade(TradeAccount tradeAccount, double amount, TradeType tradeType) {
        Date executionTime = new Date();
        String message = String.format("Processed trade for %s of amount %n type %s @ %s", tradeAccount, amount, tradeType, executionTime);
        TradeAccountEvent tradeAccountEvent;
        if (tradeType.equals(TradeType.BUY)) {
            tradeAccountEvent = new BuyEvent(tradeAccount, amount, executionTime);

        } else {
            tradeAccountEvent = new SellEvent(tradeAccount, amount, executionTime);
        }
        System.out.println(message);
        return tradeAccountEvent;
    }
}
