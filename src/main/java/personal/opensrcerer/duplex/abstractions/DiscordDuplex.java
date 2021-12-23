package personal.opensrcerer.duplex.abstractions;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import personal.opensrcerer.duplex.interfaces.Authorizable;
import personal.opensrcerer.duplex.interfaces.Duplex;
import personal.opensrcerer.duplex.interfaces.Filterable;
import personal.opensrcerer.launch.SupersonicConstants;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.EnumSet;

public abstract class DiscordDuplex<
        E extends GenericEvent
        > implements Duplex<E>, Authorizable<E>, Filterable<E>
{
    private final Class<E> type;
    protected final Flux<E> flux;
    private EnumSet<Permission> requiredPermissions;

    public DiscordDuplex(Class<E> type, Permission... permissions) {
        this.type = type;
        this.flux = Flux.create(this::consumeFluxSink, FluxSink.OverflowStrategy.BUFFER);

        if (permissions.length == 0) {
            requiredPermissions = EnumSet.noneOf(Permission.class);
            return;
        }

        requiredPermissions = EnumSet.of(permissions[0], permissions);
    }

    @Override
    public void emit() {
        this.flux.filter(this::isValid)
                .filter(this::authorize)
                .subscribe(this::receive);
    }

    private void consumeFluxSink(FluxSink<E> eFluxSink) {
        EventListener el = getEventListener(eFluxSink);
        JDA jda = SupersonicConstants.getJDA();
        jda.addEventListener(el);
        eFluxSink.onDispose(() -> jda.removeEventListener(el));
    }

    @Contract(pure = true)
    private @NotNull EventListener getEventListener(FluxSink<E> emitter) {
        return event -> {
            if (type.isInstance(event)) { // This is done to replace the usage of ListenerAdapter
                emitter.next(type.cast(event));
            }
        };
    }

    public EnumSet<Permission> getRequiredPermissions() {
        return requiredPermissions;
    }

    public void setRequiredPermissions(Permission[] requiredPermissions) {
        this.requiredPermissions = requiredPermissions.length > 0 ?
                EnumSet.of(requiredPermissions[0], requiredPermissions) :
                EnumSet.noneOf(Permission.class);
    }
}
