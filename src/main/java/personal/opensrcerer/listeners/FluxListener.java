package personal.opensrcerer.listeners;

public interface FluxListener {
    static void addListeners(FluxListener... groups) {
        for (FluxListener group : groups) {
            group.listen();
        }
    }

    void listen();
}
