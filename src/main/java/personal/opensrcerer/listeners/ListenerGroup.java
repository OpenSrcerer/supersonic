package personal.opensrcerer.listeners;

public interface ListenerGroup {
    static void addListeners(ListenerGroup... groups) {
        for (ListenerGroup group : groups) {
            group.listen();
        }
    }

    void listen();
}
