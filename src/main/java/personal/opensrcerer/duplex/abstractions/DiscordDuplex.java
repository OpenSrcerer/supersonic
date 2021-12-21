package personal.opensrcerer.duplex.abstractions;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import personal.opensrcerer.duplex.interfaces.Authorizable;
import personal.opensrcerer.duplex.interfaces.Duplex;
import personal.opensrcerer.duplex.interfaces.Filterable;
import personal.opensrcerer.launch.SupersonicConstants;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

public abstract class DiscordDuplex<
        E extends GenericEvent
        > implements Duplex<E>, Authorizable<E>, Filterable<E>
{
    private final Class<E> type;
    protected final Flux<E> flux;

    public DiscordDuplex(Class<E> type) {
        this.type = type;
        this.flux = Flux.create(this::consumeFluxSink, FluxSink.OverflowStrategy.BUFFER);
    }

    @Override
    public void emit() {
        flux.filter(this::isValid)
                .subscribe(this::receive);
    }

    private void consumeFluxSink(FluxSink<E> eFluxSink) {
        EventListener el = getEventListener(eFluxSink);
        JDA jda = SupersonicConstants.getJDA();
        jda.addEventListener(el);
        eFluxSink.onDispose(() -> jda.removeEventListener(el));
    }

    @Contract(pure = true)
    private @NotNull EventListener getEventListener(FluxSink<E> emitter) {
        return event -> {
            if (type.isInstance(event)) { // This is done to replace the usage of ListenerAdapter
                emitter.next(type.cast(event));
            }
        };
    }
}
