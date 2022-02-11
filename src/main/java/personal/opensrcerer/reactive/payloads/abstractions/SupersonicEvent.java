package personal.opensrcerer.reactive.payloads.abstractions;

import net.dv8tion.jda.api.events.GenericEvent;
import personal.opensrcerer.reactive.payloads.records.Result;

public class SupersonicEvent<E extends GenericEvent> {

    protected final E rawEvent;

    public SupersonicEvent(E rawEvent) {
        this.rawEvent = rawEvent;
    }

    public E raw() {
        return rawEvent;
    }

    public Result evaluate() {
        return null;
    }
}
