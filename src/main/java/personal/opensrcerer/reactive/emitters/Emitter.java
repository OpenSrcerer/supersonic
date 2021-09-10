package personal.opensrcerer.reactive.emitters;

/**
 * Groups all Event Emitters together.
 */
public interface Emitter {
    /**
     * Makes the provided emitters subscribe to their respective sinks.
     * @param emitters Emitters to call emit() for.
     */
    static void addListeners(Emitter... emitters) {
        for (Emitter emitter : emitters) {
            emitter.emit();
        }
    }

    /**
     * Subscribes to a sink and emits events when received.
     */
    void emit();
}
