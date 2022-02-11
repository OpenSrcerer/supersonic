package personal.opensrcerer.reactive.subscribers.abstractions;

import net.dv8tion.jda.api.events.GenericEvent;
import personal.opensrcerer.reactive.payloads.abstractions.SupersonicEvent;

public interface Superscriber<
        E extends GenericEvent,
        R extends SupersonicEvent<E>>
{
    void onEvent(R event);

    void subscribe();
}
