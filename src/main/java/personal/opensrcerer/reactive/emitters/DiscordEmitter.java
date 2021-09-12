package personal.opensrcerer.reactive.emitters;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import personal.opensrcerer.launch.SupersonicConstants;
import personal.opensrcerer.reactive.sinks.Sink;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

/**
 * An implementation of Emitter for Discord Events.
 * @param <E> Type of JDA event to handle.
 * @see GenericEvent
 */
public abstract class DiscordEmitter<E extends GenericEvent> implements Emitter {

    private final Class<E> type;
    private final Flux<E> flux;
    private final Sink<E> sink;

    public DiscordEmitter(Class<E> type, Sink<E> sink) {
        this.type = type;
        this.sink = sink;
        this.flux = Flux.create(emitter -> {
            EventListener el = getEventListener(emitter);
            JDA jda = SupersonicConstants.getJDA();
            jda.addEventListener(el);
            emitter.onDispose(() -> jda.removeEventListener(el));
        }, FluxSink.OverflowStrategy.BUFFER);
    }

    @Contract(pure = true)
    private @NotNull EventListener getEventListener(FluxSink<E> emitter) {
        return event -> {
            if (type.isInstance(event)) { // This is done to replace the usage of ListenerAdapter
                emitter.next(type.cast(event));
            }
        };
    }

    @Override
    public void emit() {
        flux.subscribe(sink::receive);
    }

    public Flux<E> flux() {
        return this.flux;
    }

    public Sink<E> sink() {
        return this.sink;
    }

    public boolean filterValid(final E e) {
        return true;
    }
}
