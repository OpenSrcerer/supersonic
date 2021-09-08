package personal.opensrcerer.handlers;

import personal.opensrcerer.reactive.DiscordEventEmitter;

/**
 * Handles events emitted by Emitters.
 * @param <E> Raw JDA event emitted.
 * @param <M> Parsed type of event emitted.
 * @see DiscordEventEmitter
 */
@FunctionalInterface
public interface Sink<E, M> {
    void receive(M m);

    default boolean isValid(E e) {
        return true;
    }
}
