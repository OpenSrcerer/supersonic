package personal.opensrcerer.reactive.sinks;

import net.dv8tion.jda.api.events.GenericEvent;
import personal.opensrcerer.reactive.emitters.DiscordEmitter;

/**
 * Handles events emitted by Emitters.
 * @param <E> Type of event to receive.
 * @see DiscordEmitter
 */
@FunctionalInterface
public interface Sink<E extends GenericEvent> {
    void receive(E e);
}
