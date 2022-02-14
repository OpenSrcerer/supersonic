package personal.opensrcerer.reactive;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import personal.opensrcerer.launch.SupersonicConstants;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public final class EventMulticaster {

    private static Flux<GenericEvent> multicastFlux = null;
    private static EventListener listener;

    private EventMulticaster() {}

    private static Flux<GenericEvent> getFlux() {
        if (multicastFlux == null) {
            var jdaFlux = Flux.<GenericEvent>create(sink -> {
                listener = sink::next;
                SupersonicConstants.getJDA().addEventListener(listener);
                sink.onDispose(() -> SupersonicConstants.getJDA().removeEventListener(listener));
            });
            multicastFlux = jdaFlux.publishOn(Schedulers.single());
        }
        return multicastFlux;
    }

    public static <E extends GenericEvent> void inject(E e) {
        listener.onEvent(e);
    }

    public static <E extends GenericEvent> Flux<E> of(Class<E> eventToCapture) {
        return getFlux().filter(eventToCapture::isInstance)
                .map(eventToCapture::cast);
    }
}
