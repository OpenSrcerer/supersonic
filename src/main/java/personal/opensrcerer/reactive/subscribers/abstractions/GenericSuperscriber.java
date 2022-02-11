package personal.opensrcerer.reactive.subscribers.abstractions;

import net.dv8tion.jda.api.events.GenericEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import personal.opensrcerer.aspect.mapping.MappingStrategy;
import personal.opensrcerer.reactive.EventMulticaster;
import personal.opensrcerer.reactive.payloads.abstractions.SupersonicEvent;

public abstract class GenericSuperscriber<
        E extends GenericEvent,
        R extends SupersonicEvent<E>
        > implements Superscriber<E, R>
{
    private static final Logger logger = LoggerFactory.getLogger(GenericSuperscriber.class);

    protected Class<E> eventToCapture;
    protected MappingStrategy<E, R> mappingStrategy;

    @Override
    public void subscribe() {
        EventMulticaster.of(eventToCapture)
                .map(mappingStrategy)
                .doOnNext(this::onEvent)
                .checkpoint()
                .onErrorContinue((t, r) -> this.onError(t, (R) r))
                .subscribe();
    }

    protected void onError(Throwable throwable, R mappedEvent) {
        logger.error("Oh noes! :( Supersonic got an exception:", throwable);
    }

    public void setTranslation(
            Class<?> eventToCapture,
            MappingStrategy<?, ?> mappingStrategy
    ) {
        this.eventToCapture = (Class<E>) eventToCapture;
        this.mappingStrategy = (MappingStrategy<E, R>) mappingStrategy;
    }
}
