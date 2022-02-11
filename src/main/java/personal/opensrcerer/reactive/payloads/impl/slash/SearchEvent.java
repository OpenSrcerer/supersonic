package personal.opensrcerer.reactive.payloads.impl.slash;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.reactive.payloads.abstractions.SupersonicSlashCommandEvent;

public class SearchEvent extends SupersonicSlashCommandEvent {
    public SearchEvent(SlashCommandEvent event) {
        super(event);
    }
}
