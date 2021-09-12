package personal.opensrcerer.reactive.emitters;

import net.dv8tion.jda.api.events.GenericEvent;
import personal.opensrcerer.reactive.sinks.Sink;
import personal.opensrcerer.requests.SubsonicRequest;

public abstract class SubsonicDiscordEmitter<
        E extends GenericEvent,
        R extends SubsonicRequest<R>
        > extends DiscordEmitter<E> {

    public SubsonicDiscordEmitter(Class<E> type, Sink<E> sink) {
        super(type, sink);
    }

    @Override
    public void emit() {
    }
}
