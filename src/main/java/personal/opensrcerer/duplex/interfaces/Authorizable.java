package personal.opensrcerer.duplex.interfaces;

/**
 * @param <E> Event to authorize.
 */
public interface Authorizable<E> {
    /**
     * @return True if given condition is passed.
     */
    default boolean authorize(E e) {
        return true;
    }
}
