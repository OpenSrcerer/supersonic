package personal.opensrcerer.duplex.payloads.impl;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.duplex.payloads.interfaces.SupersonicSlashCommandEvent;

public record PlayEvent(SlashCommandEvent event)
        implements SupersonicSlashCommandEvent {

    @Override
    public SlashCommandEvent raw() {
        return this.event;
    }
}
