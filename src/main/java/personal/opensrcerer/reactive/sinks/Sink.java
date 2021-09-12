package personal.opensrcerer.reactive.sinks;

import personal.opensrcerer.reactive.emitters.DiscordEmitter;

/**
 * Handles events emitted by Emitters.
 * @param <E> Type of event to receive.
 * @see DiscordEmitter
 */
@FunctionalInterface
public interface Sink<E> {
    void receive(E e);
}
