package personal.opensrcerer.reactive.payloads.impl.slash;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.reactive.payloads.Maybe;
import personal.opensrcerer.reactive.payloads.abstractions.SupersonicSlashCommandEvent;

public class MumEvent extends SupersonicSlashCommandEvent {
    public MumEvent(SlashCommandEvent event) {
        super(event);
    }

    @Override
    public Maybe<String> evaluate() {
        return new Maybe<>((rawEvent.getUser().getId().equals("178603029115830282")) ? "Yes." : "No.");
    }
}
