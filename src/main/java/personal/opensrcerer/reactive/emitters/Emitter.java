package personal.opensrcerer.reactive.emitters;

/**
 * Groups all Event Emitters together.
 */
@FunctionalInterface
public interface Emitter {
    /**
     * Subscribes to a sink and emits events when received.
     */
    void emit();
}
