package fluxTests.mockEvents;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.reactive.payloads.abstractions.SupersonicSlashCommandEvent;

public class BlahEvent extends SupersonicSlashCommandEvent {
    public BlahEvent(SlashCommandEvent event) {
        super(event);
    }
}
