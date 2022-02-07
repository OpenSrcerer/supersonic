package personal.opensrcerer.duplex.interfaces;

public interface Duplex<E> extends Emitter, Sink<E> {
    default boolean authorize(final E e) {
        return true;
    }

    default boolean isValid(final E e) {
        return true;
    }

    void inject
}
