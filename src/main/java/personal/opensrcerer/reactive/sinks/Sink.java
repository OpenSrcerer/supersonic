package personal.opensrcerer.reactive.sinks;

import net.dv8tion.jda.api.events.GenericEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import personal.opensrcerer.reactive.emitters.DiscordEmitter;

/**
 * Handles events emitted by Emitters.
 * @param <E> Type of event to receive.
 * @see DiscordEmitter
 */
@FunctionalInterface
public interface Sink<E extends GenericEvent> {
    Logger lgr = LoggerFactory.getLogger(Sink.class);

    void onEvent(E e);

    default void onError(E e, Throwable t) {
        lgr.error("Unhandled exception:", t);
    }

    default void receive(E e) {
        try {
            onEvent(e);
        } catch (Exception ex) {
            onError(e, ex);
        }
    }
}
