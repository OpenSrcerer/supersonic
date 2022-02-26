package personal.opensrcerer.reactive.payloads.impl.slash.player;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.reactive.payloads.Maybe;
import personal.opensrcerer.reactive.payloads.abstractions.SupersonicSlashCommandEvent;

public class SkipEvent extends SupersonicSlashCommandEvent {
    public SkipEvent(SlashCommandEvent event) {
        super(event);
    }

    @Override
    public Maybe<Void> evaluate() {
        return new Maybe<>(null);
    }
}
