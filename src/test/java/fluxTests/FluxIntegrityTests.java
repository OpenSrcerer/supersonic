package fluxTests;

import fluxTests.mockEvents.SlashCommandBlahEvent;
import fluxTests.mockMulticaster.TestMockEventMulticaster;
import fluxTests.mockSubscribers.DataOrderSubscriber;
import fluxTests.mockSubscribers.EventCountingSubscriber;
import fluxTests.mockSubscribers.ExceptionSubscriber;
import fluxTests.mockSubscribers.MockSubscriber;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static fluxTests.mockMulticaster.TestMockEventMulticaster.TEST_EVENTS;
import static fluxTests.mockMulticaster.TestMockEventMulticaster.TEST_SUBSCRIBERS;

public class FluxIntegrityTests {
    private static final Logger logger = LoggerFactory.getLogger(FluxIntegrityTests.class);

    @Test
    void validateFluxEvent_NoDrop() {
        List<EventCountingSubscriber> eventCountingSubscribers = new LinkedList<>();
        for (int i = 0; i < TEST_SUBSCRIBERS; ++i) {
            eventCountingSubscribers.add(new EventCountingSubscriber());
        }
        eventCountingSubscribers.forEach(EventCountingSubscriber::subscribe);
        injectFakeEvents();

        AtomicInteger passed = new AtomicInteger();
        AtomicInteger progress = new AtomicInteger();
        eventCountingSubscribers.forEach(sub -> {
            progress.incrementAndGet();
            double progressRemainder = progress.get() / (double) TEST_SUBSCRIBERS;
            if (progress.get() % (TEST_SUBSCRIBERS / 10) == 0) {
                logger.info("No Drop - Progress: " + progressRemainder * 100 + "%" + " (" + progress.get() + "/" + TEST_SUBSCRIBERS + ")");
            }

            Assertions.assertEquals(TEST_EVENTS, sub.getEventPassthrough());
            passed.incrementAndGet();
        });
        logger.info("[No Drop] - Failed: " + (TEST_SUBSCRIBERS - passed.get()) + "\n");
    }

    @Test
    void testErrorHandling_IfSubscriber_Throws() {
        ExceptionSubscriber exceptionSubscriber = new ExceptionSubscriber();
        exceptionSubscriber.subscribe();
        TestMockEventMulticaster.inject(new SlashCommandBlahEvent());
        logger.info("[Top Error Handling] - Passed" + "\n");
    }

    @Test
    void testSubscriber_DataOrder() {
        List<DataOrderSubscriber> dataOrderSubscribers = new LinkedList<>();
        for (int i = 0; i < TEST_SUBSCRIBERS; i++) {
            dataOrderSubscribers.add(new DataOrderSubscriber());
        }
        dataOrderSubscribers.forEach(MockSubscriber::subscribe);
        injectFakeEvents();
        var dataList = dataOrderSubscribers.stream()
                .map(DataOrderSubscriber::getDataOrderList)
                .toList();

        AtomicInteger passed = new AtomicInteger();
        AtomicInteger progress = new AtomicInteger();
        dataList.forEach(list -> {
            progress.incrementAndGet();
            double progressRemainder = progress.get() / (double) TEST_SUBSCRIBERS;
            if (progress.get() % (TEST_SUBSCRIBERS / 10) == 0) {
                logger.info("Data Order - Progress: " + progressRemainder * 100 + "%" + " (" + progress.get() + "/" + TEST_SUBSCRIBERS + ")");
            }

            Assertions.assertEquals(1,  list.get(0));
            Assertions.assertEquals(TEST_EVENTS,  list.get(TEST_EVENTS - 1));
            passed.incrementAndGet();
        });
        logger.info("[Data Order] - Failures: " + (TEST_SUBSCRIBERS - passed.get()) + "\n");
    }

    private void injectFakeEvents() {
        for (int i = 0; i < TEST_EVENTS; i++) {
            TestMockEventMulticaster.inject(new SlashCommandBlahEvent());
        }
    }
}
