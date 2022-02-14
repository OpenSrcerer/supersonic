package personal.opensrcerer.reactive.payloads.impl.slash;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.messaging.constant.ConstantEmbeds;
import personal.opensrcerer.reactive.payloads.Maybe;
import personal.opensrcerer.reactive.payloads.abstractions.SupersonicSlashCommandEvent;

public class LeaveEvent extends SupersonicSlashCommandEvent {
    public LeaveEvent(SlashCommandEvent event) {
        super(event);
    }

    @Override
    public Maybe<Void> evaluate() {
        boolean botConnected = getAudioManager().isConnected();
        boolean memberConnected = getMemberVoiceState().inVoiceChannel();

        if (!botConnected) {
            return new Maybe<>(ConstantEmbeds.Companion.plainEmbed(
                    "Not connected",
                    "I cannot leave a voice channel if I am not connected to one."
            ));
        }

        if (!memberConnected || !getMemberVoiceState().getChannel().getId().equals(getAudioManager().getConnectedChannel().getId())) {
            return new Maybe<>(ConstantEmbeds.Companion.plainEmbed(
                    "Join my voice channel",
                    "Join <#" + getAudioManager().getConnectedChannel().getId() + "> to be able to control the bot!"
            ));
        }

        return new Maybe<>(null);
    }
}
