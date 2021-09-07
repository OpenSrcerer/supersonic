package personal.opensrcerer.reactive;

public interface Emitter {
    static void addListeners(Emitter... emitters) {
        for (Emitter emitter : emitters) {
            emitter.emit();
        }
    }

    void emit();
}
