package personal.opensrcerer.reactive.payloads.impl.slash;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.reactive.payloads.abstractions.SupersonicSlashCommandEvent;

public class LeaveEvent extends SupersonicSlashCommandEvent {
    public LeaveEvent(SlashCommandEvent event) {
        super(event);
    }
}
