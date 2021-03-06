package personal.opensrcerer.reactive.payloads.impl.slash;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.reactive.payloads.Maybe;
import personal.opensrcerer.reactive.payloads.abstractions.SupersonicSlashCommandEvent;

public class PauseEvent extends SupersonicSlashCommandEvent {
    public PauseEvent(SlashCommandEvent event) {
        super(event);
    }

    @Override
    public Maybe<Void> evaluate() {
        return new Maybe<>(null);
    }
}
