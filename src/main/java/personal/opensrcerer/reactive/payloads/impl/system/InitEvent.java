package personal.opensrcerer.reactive.payloads.impl.system;

import net.dv8tion.jda.api.events.ReadyEvent;
import personal.opensrcerer.reactive.payloads.Maybe;
import personal.opensrcerer.reactive.payloads.abstractions.SupersonicEvent;

public class InitEvent extends SupersonicEvent<ReadyEvent> {
    public InitEvent(ReadyEvent event) {
        super(event);
    }

    @Override
    public Maybe<?> evaluate() {
        return null;
    }
}
