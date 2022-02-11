package fluxTests;

import fluxTests.mockEvents.BlahEvent;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.aspect.mapping.MappingStrategy;
import personal.opensrcerer.reactive.payloads.abstractions.SupersonicEvent;

public interface MockMappingStrategy<
        E extends GenericEvent,
        R extends SupersonicEvent<E>
        > extends MappingStrategy<E, R> {
    MockMappingStrategy<SlashCommandEvent, BlahEvent> slashCommandEventToBlahEvent = BlahEvent::new;
}
