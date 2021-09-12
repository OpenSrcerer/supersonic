package personal.opensrcerer.reactive.sinks;

import net.dv8tion.jda.api.Permission;
import personal.opensrcerer.reactive.emitters.DiscordEmitter;

import java.util.EnumSet;

/**
 * Handles events that need to check if necessary setup was made or if the bot has the required permissions
 * before sending back a reply.
 * @param <E> Type of event to receive.
 * @see Sink
 * @see DiscordEmitter
 */
public abstract class AuthorizableSink<E> implements Sink<E> {
    private final EnumSet<Permission> requiredPermissions;

    public AuthorizableSink(Permission[] permissions) {
        if (permissions.length == 0) {
            requiredPermissions = EnumSet.noneOf(Permission.class);
            return;
        }

        requiredPermissions = EnumSet.of(permissions[0], permissions);
    }

    public EnumSet<Permission> permissions() {
        return requiredPermissions;
    }

    public abstract boolean authorize(E e);
}
