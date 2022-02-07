package personal.opensrcerer.duplex.payloads.interfaces;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public abstract class SupersonicSlashCommandEvent implements SupersonicEvent {
    protected final SlashCommandEvent event;

    public SupersonicSlashCommandEvent(SlashCommandEvent event) {
        this.event = event;
    }
}
