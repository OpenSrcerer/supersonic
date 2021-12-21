package personal.opensrcerer.duplex.abstractions;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.GenericEvent;

import java.util.EnumSet;

public abstract class AuthDiscordDuplex<
        E extends GenericEvent
        > extends DiscordDuplex<E>
{
    private final EnumSet<Permission> requiredPermissions;

    public AuthDiscordDuplex(Class<E> type, Permission[] permissions) {
        super(type);

        if (permissions.length == 0) {
            requiredPermissions = EnumSet.noneOf(Permission.class);
            return;
        }

        requiredPermissions = EnumSet.of(permissions[0], permissions);
    }

    public EnumSet<Permission> requiredPermissions() {
        return requiredPermissions;
    }
}
