package personal.opensrcerer.reactive.payloads.impl.slash.player;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.messaging.constant.ConstantEmbeds;
import personal.opensrcerer.reactive.payloads.Maybe;
import personal.opensrcerer.reactive.payloads.abstractions.SupersonicSlashCommandEvent;

public class SearchEvent extends SupersonicSlashCommandEvent {
    public SearchEvent(SlashCommandEvent event) {
        super(event);
    }

    @Override
    public Maybe<String> evaluate() {
        if (rawEvent.getOption("query") == null) {
            return new Maybe<>(ConstantEmbeds.Companion.plainEmbed(
                    "The slash command you input was invalid",
                    "Please check your arguments and try again."
            ));
        }

        return new Maybe<>(rawEvent.getOption("query").getAsString());
    }
}
