package personal.opensrcerer.reactive.subscribers.abstractions;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.GenericEvent;

public interface Authorizable<E extends GenericEvent> {
    boolean authorize(E event);

    void setRequiredPermissions(Permission[] requiredPermissions);
}
