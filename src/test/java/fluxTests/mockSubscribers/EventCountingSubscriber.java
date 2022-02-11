package fluxTests.mockSubscribers;

import fluxTests.mockEvents.BlahEvent;

import java.util.concurrent.atomic.AtomicInteger;

public class EventCountingSubscriber extends MockSubscriber {
    private final AtomicInteger integer = new AtomicInteger(0);

    @Override
    public void onEvent(BlahEvent event) {
        integer.incrementAndGet();
    }

    public int getEventPassthrough() {
        return integer.intValue();
    }
}
