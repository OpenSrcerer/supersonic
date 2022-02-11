package personal.opensrcerer.reactive.payloads.abstractions;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class SupersonicSlashCommandEvent extends SupersonicEvent<SlashCommandEvent> {
    public SupersonicSlashCommandEvent(SlashCommandEvent event) {
        super(event);
    }
}
