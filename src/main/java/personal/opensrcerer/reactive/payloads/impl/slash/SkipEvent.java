package personal.opensrcerer.reactive.payloads.impl.slash;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.reactive.payloads.Maybe;
import personal.opensrcerer.reactive.payloads.abstractions.SupersonicSlashCommandEvent;

public class SkipEvent extends SupersonicSlashCommandEvent {
    public SkipEvent(SlashCommandEvent event) {
        super(event);
    }

    @Override
    public Maybe<?> evaluate() {
        return null;
    }
}
