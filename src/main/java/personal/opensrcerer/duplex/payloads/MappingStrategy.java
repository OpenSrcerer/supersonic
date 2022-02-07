package personal.opensrcerer.duplex.payloads;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.duplex.payloads.impl.PlayEvent;
import personal.opensrcerer.duplex.payloads.interfaces.SupersonicEvent;

import java.util.function.Function;

public interface MappingStrategy<
        E, R extends SupersonicEvent
        > extends Function<E, R> {
    MappingStrategy<SlashCommandEvent, PlayEvent> slashCommandEventToPlayEventStrategy = PlayEvent::new;
}
