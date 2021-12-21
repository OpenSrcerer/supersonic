package personal.opensrcerer.duplex.interfaces;

import net.dv8tion.jda.api.events.GenericEvent;

public interface Duplex<E extends GenericEvent> extends Emitter, Sink<E> {
}
