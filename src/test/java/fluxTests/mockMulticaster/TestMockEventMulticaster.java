package fluxTests.mockMulticaster;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import reactor.core.publisher.Flux;

public class TestMockEventMulticaster {
    public static final int TEST_SUBSCRIBERS = 100;
    public static final int TEST_EVENTS = 10000;

    private static Flux<GenericEvent> mockFlux = null;
    private static EventListener listener;

    private TestMockEventMulticaster() {}

    private static Flux<GenericEvent> getMockFlux() {
        if (mockFlux == null) {
            mockFlux = Flux.<GenericEvent>create(sink -> listener = sink::next)
                    .publish()
                    .autoConnect(TEST_SUBSCRIBERS);
        }
        return mockFlux;
    }

    public static <E extends GenericEvent> void inject(E e) {
        if (mockFlux == null) {
            getMockFlux();
        }
        listener.onEvent(e);
    }

    public static <E extends GenericEvent> Flux<E> of(Class<E> eventToCapture) {
        return getMockFlux()
                .filter(eventToCapture::isInstance)
                .map(eventToCapture::cast);
    }
}
