package personal.opensrcerer.reactive;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import personal.opensrcerer.launch.SupersonicRuntimeConstants;
import personal.opensrcerer.reactive.listeners.FluxListener;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

/**
 * Provides an interface for the creation of specialized fluxes that treat
 * different types of Discord events wrapped by JDA.
 * @param <X> Type of event to handle.
 * @see GenericEvent
 */
public abstract class DiscordFlux<X extends GenericEvent> implements FluxListener {

    private final Class<X> type;
    private final Flux<X> messageFlux;

    public DiscordFlux(Class<X> type) {
        this.type = type;
        this.messageFlux = Flux.create(emitter -> {
            JDA jda = SupersonicRuntimeConstants.getJDA();
            EventListener el = getEventListener(emitter);
            jda.addEventListener(el);
            emitter.onDispose(() -> jda.removeEventListener(el));
        });
    }

    private EventListener getEventListener(FluxSink<X> emitter) {
        return event -> {
            if (type.isInstance(event)) {
                emitter.next(type.cast(event));
            }
        };
    }

    public Flux<X> flux() {
        return this.messageFlux;
    }
}
