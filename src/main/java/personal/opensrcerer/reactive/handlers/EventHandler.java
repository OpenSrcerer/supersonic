package personal.opensrcerer.reactive.handlers;

public interface EventHandler<E, M> {
    void handle(M m);

    boolean isValid(E e);
}
