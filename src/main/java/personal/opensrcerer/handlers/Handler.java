package personal.opensrcerer.handlers;

import personal.opensrcerer.reactive.DiscordEventEmitter;

/**
 * Handles events emitted by DiscordFluxes.
 * @param <E> Raw JDA event emitted.
 * @param <M> Parsed type of event emitted.
 * @see DiscordEventEmitter
 */
public interface Handler<E, M> {
    void handle(M m);

    default boolean isValid(E e) {
        return true;
    }
}
