package personal.opensrcerer.reactive.payloads.impl.slash;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.reactive.payloads.abstractions.SupersonicEvent;
import personal.opensrcerer.reactive.payloads.abstractions.SupersonicSlashCommandEvent;

public class UnpauseEvent extends SupersonicSlashCommandEvent {
    public UnpauseEvent(SlashCommandEvent event) {
        super(event);
    }
}
