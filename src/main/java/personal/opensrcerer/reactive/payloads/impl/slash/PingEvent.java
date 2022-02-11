package personal.opensrcerer.reactive.payloads.impl.slash;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.reactive.payloads.abstractions.SupersonicSlashCommandEvent;

public class PingEvent extends SupersonicSlashCommandEvent {
    public PingEvent(SlashCommandEvent event) {
        super(event);
    }
}
