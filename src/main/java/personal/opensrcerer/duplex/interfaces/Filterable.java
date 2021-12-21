package personal.opensrcerer.duplex.interfaces;

public interface Filterable<E> {
    /**
     * @param e Object to check.
     * @return If the given object is valid in the given context.
     */
    default boolean isValid(final E e) {
        return true;
    }
}
