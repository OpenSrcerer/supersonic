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

public abstract class DefaultDuplex<E>
        implements Duplex<E>, Authorizable<E>, Filterable<E>
{
    protected final Flux<E> flux;
    protected final Class<E> jdaEventType;

    private EnumSet<Permission> requiredPermissions;

    public DefaultDuplex(Class<E> jdaEventType) {
        this.jdaEventType = jdaEventType;
        this.flux = Flux.create(this::consumeFluxSink, FluxSink.OverflowStrategy.BUFFER);
    }

    @Override
    public final void emit() {
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
    protected @NotNull EventListener getEventListener(FluxSink<E> emitter) {
        return event -> {
            if (jdaEventType.isInstance(event)) { // Find if the event type matches
                emitter.next(jdaEventType.cast(event)); // If it does, cast
            }
        };
    }

    public final EnumSet<Permission> getRequiredPermissions() {
        return requiredPermissions;
    }

    public final void setRequiredPermissions(Permission[] requiredPermissions) {
        this.requiredPermissions = requiredPermissions.length > 0 ?
                EnumSet.of(requiredPermissions[0], requiredPermissions) :
                EnumSet.noneOf(Permission.class);
    }
}