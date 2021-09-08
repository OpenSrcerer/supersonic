package personal.opensrcerer.sinks;

import personal.opensrcerer.reactive.DiscordEventEmitter;

/**
 * Handles events emitted by Emitters.
 * @param <E> Type of event emitted.
 * @see DiscordEventEmitter
 */
@FunctionalInterface
public interface Sink<E> {
    void receive(E e);
}
