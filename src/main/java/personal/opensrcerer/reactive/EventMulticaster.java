package personal.opensrcerer.reactive;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import personal.opensrcerer.launch.SupersonicConstants;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

public final class EventMulticaster {

    private static Flux<GenericEvent> multicastFlux = null;
    private static Flux<GenericEvent> mockFlux = null;
    private static EventListener listener;

    private EventMulticaster() {}

    private static Flux<GenericEvent> getFlux() {
        if (multicastFlux == null) {
            var jdaFlux = Flux.<GenericEvent>create(sink -> {
                EventListener listener = sink::next;
                SupersonicConstants.getJDA().addEventListener(listener);
            });
            multicastFlux = jdaFlux.concatWith(getMockFlux()).publish().autoConnect();
        }
        return multicastFlux;
    }

    private static Flux<GenericEvent> getMockFlux() {
        if (mockFlux == null) {
            mockFlux = Flux.<GenericEvent>create(sink -> listener = sink::next).publish().autoConnect();
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
        return getFlux().filter(eventToCapture::isInstance)
                .map(eventToCapture::cast);
    }

    public static <E extends GenericEvent> Flux<E> ofMock(Class<E> eventToCapture) {
        return getMockFlux().filter(eventToCapture::isInstance)
                .map(eventToCapture::cast);
    }
}
