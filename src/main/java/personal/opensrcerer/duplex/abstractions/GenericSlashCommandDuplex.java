package personal.opensrcerer.duplex.abstractions;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.duplex.payloads.interfaces.SupersonicSlashCommandEvent;

public abstract class GenericSlashCommandDuplex <
        R extends SupersonicSlashCommandEvent
        > extends GenericDuplex<SlashCommandEvent, R>
{
    public GenericSlashCommandDuplex(String commandName) {
        super(SlashCommandEvent.class);
    }
}
