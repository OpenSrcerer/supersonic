package personal.opensrcerer.duplex.abstractions;

import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import personal.opensrcerer.duplex.payloads.interfaces.MappingStrategy;
import personal.opensrcerer.duplex.payloads.interfaces.SupersonicEvent;
import reactor.core.publisher.FluxSink;

public abstract class GenericDuplex<
        E, R extends SupersonicEvent<E>
        > extends DefaultDuplex<R>
{
    private final Class<E> jdaEventType;

    private MappingStrategy<E, R> strategy;

    public GenericDuplex(Class<E> jdaEventType) {
        super(null);
        this.jdaEventType = jdaEventType;
    }

    @Contract(pure = true)
    protected final @NotNull EventListener getEventListener(FluxSink<R> emitter) {
        return event -> {
            if (jdaEventType.isInstance(event)) { // Find if the event type matches
                emitter.next(strategy.apply(jdaEventType.cast(event))); // If it does, cast and map
            }
        };
    }

    @SuppressWarnings("unchecked")
    public final void setStrategy(MappingStrategy<?, ?> strategy) {
        this.strategy = (MappingStrategy<E, R>) strategy;
    }
}
