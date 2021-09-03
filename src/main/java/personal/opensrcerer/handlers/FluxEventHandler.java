package personal.opensrcerer.handlers;

/**
 * Handles events emitted by DiscordFluxes.
 * @param <E> Raw JDA event emitted.
 * @param <M> Parsed type of event emitted.
 * @see personal.opensrcerer.reactive.DiscordFlux
 */
public interface FluxEventHandler<E, M> {
    void handle(M m);

    boolean isValid(E e);
}
