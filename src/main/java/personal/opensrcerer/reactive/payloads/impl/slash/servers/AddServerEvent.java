package personal.opensrcerer.reactive.payloads.impl.slash.servers;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.reactive.payloads.Maybe;
import personal.opensrcerer.reactive.payloads.abstractions.SupersonicSlashCommandEvent;

public class AddServerEvent extends SupersonicSlashCommandEvent {
    public AddServerEvent(SlashCommandEvent event) {
        super(event);
    }

    @Override
    public Maybe<?> evaluate() {
        return null;
    }
}
