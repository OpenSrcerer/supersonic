package personal.opensrcerer.reactive.sinks;

import personal.opensrcerer.reactive.emitters.DiscordEventEmitter;

/**
 * Handles events emitted by Emitters.
 * @param <E> Type of event to receive.
 * @see DiscordEventEmitter
 */
@FunctionalInterface
public interface Sink<E> {
    void receive(E e);
}
