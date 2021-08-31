package personal.opensrcerer.reactive;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import personal.opensrcerer.launch.ReactorApplicationRuntimeConstants;
import personal.opensrcerer.listeners.ListenerGroup;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

public abstract class DiscordFlux<X extends GenericEvent> implements ListenerGroup {

    private final Class<X> type;
    private final Flux<X> messageFlux;

    public DiscordFlux(Class<X> type) {
        this.type = type;
        this.messageFlux = Flux.create(emitter -> {
            JDA jda = ReactorApplicationRuntimeConstants.getJDA();
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
