package fluxTests.mockSubscribers;

import fluxTests.mockEvents.BlahEvent;

import java.util.LinkedList;

public class DataOrderSubscriber extends MockSubscriber {
    private final LinkedList<Long> dataOrderList = new LinkedList<>();

    @Override
    public void onEvent(BlahEvent event) {
        dataOrderList.add(event.raw().getResponseNumber());
    }

    public LinkedList<Long> getDataOrderList() {
        return dataOrderList;
    }
}
