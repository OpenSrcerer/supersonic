package personal.opensrcerer.duplex.payloads.interfaces;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.duplex.payloads.impl.PlayEvent;

import java.util.function.Function;

public interface MappingStrategy<
        E, R extends SupersonicEvent<E>
        > extends Function<E, R> {
    MappingStrategy<SlashCommandEvent, PlayEvent> slashCommandEventToPlayEventStrategy = PlayEvent::new;
}
