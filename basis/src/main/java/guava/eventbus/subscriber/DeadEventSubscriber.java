package guava.eventbus.subscriber;

import com.google.common.collect.Lists;
import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.util.List;

/**
 * User: Bill Bejeck
 * Date: 4/26/13
 * Time: 3:18 PM
 */
public class DeadEventSubscriber {

    private List<DeadEvent> deadEvents = Lists.newArrayList();

    public DeadEventSubscriber(EventBus eventBus) {
           eventBus.register(this);
    }

    @Subscribe
    public void handleUnsubscribedEvent(DeadEvent deadEvent){
        System.out.println("No subscribers for "+deadEvent.getEvent());
        deadEvents.add(deadEvent);
    }

    public List<DeadEvent> getDeadEvents() {
        return deadEvents;
    }
}
