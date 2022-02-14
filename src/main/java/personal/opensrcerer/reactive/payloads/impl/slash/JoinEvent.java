package personal.opensrcerer.reactive.payloads.impl.slash;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.messaging.constant.ConstantEmbeds;
import personal.opensrcerer.reactive.payloads.Maybe;
import personal.opensrcerer.reactive.payloads.abstractions.SupersonicSlashCommandEvent;

public class JoinEvent extends SupersonicSlashCommandEvent {
    public JoinEvent(SlashCommandEvent event) {
        super(event);
    }

    @Override
    public Maybe<Boolean> evaluate() {
        if (!memberInVoice()) {
            return new Maybe<>(ConstantEmbeds.Companion.plainEmbed(
                    "Join a voice channel",
                    "Please join a voice channel before you use this command."
            ));
        }

        if (isBotConnected() && getAudioManager().getConnectedChannel().getId().equals(getMemberVoiceChannel().getId())) {
            return new Maybe<>(ConstantEmbeds.Companion.plainEmbed(
                    "Already in channel",
                    "The bot is already in <#" + getMemberVoiceChannel().getId() + ">"
            ));
        }

        if (isBotConnected() && !getAudioManager().getConnectedChannel().getId().equals(getMemberVoiceChannel().getId())) {
            return new Maybe<>(true);
        }

        return new Maybe<>(false);
    }
}
