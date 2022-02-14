package fluxTests.mockSubscribers;

import fluxTests.mockEvents.BlahEvent;

public class ExceptionSubscriber extends MockSubscriber {
    @Override
    public void onEvent(BlahEvent event) {
        throw new NullPointerException();
    }
}
