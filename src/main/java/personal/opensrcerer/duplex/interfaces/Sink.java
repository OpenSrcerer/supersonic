package personal.opensrcerer.duplex.interfaces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import personal.opensrcerer.reactive.emitters.DiscordEmitter;

/**
 * Handles events emitted by Emitters.
 * @param <E> Type of event to receive.
 * @see DiscordEmitter
 */
@FunctionalInterface
public interface Sink<E> {
    Logger lgr = LoggerFactory.getLogger(Sink.class);

    /**
     * Perform an action on a receiving an event.
     */
    void onEvent(E e);

    /**
     * Perform an action on a receiving an error.
     * @param e Relevant erroneous event.
     * @param t Error that was thrown.
     */
    default void onError(E e, Throwable t) {
        lgr.error("Oh noes! :( Supersonic got an exception:", t);
    }

    /**
     * Accept an event.
     */
    default void receive(E e) {
        try {
            onEvent(e);
        } catch (Exception ex) {
            onError(e, ex);
        }
    }
}
