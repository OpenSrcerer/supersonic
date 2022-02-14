package personal.opensrcerer.reactive.payloads.impl.slash;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.managers.AudioManager;
import personal.opensrcerer.messaging.constant.ConstantEmbeds;
import personal.opensrcerer.reactive.payloads.abstractions.SupersonicSlashCommandEvent;
import personal.opensrcerer.reactive.payloads.mock.EmbedPlayEvent;
import personal.opensrcerer.reactive.payloads.Maybe;

public class PlayEvent extends SupersonicSlashCommandEvent {
    public PlayEvent(SlashCommandEvent event) {
        super(event);
    }

    @Override
    public Maybe<String> evaluate() {
        String botChannelId = (getAudioManager().isConnected()) ? getAudioManager().getConnectedChannel().getId() : null;
        String memberChannelId = (getMemberVoiceState().inVoiceChannel()) ?
                getMemberVoiceChannel().getId() : null;

        if (botChannelId == null && memberChannelId == null) {
            return new Maybe<>(
                    ConstantEmbeds.Companion.plainEmbed(
                            "Join a voice channel",
                            "You must join a voice channel to use this command!"
                    ));
        }

        if ((memberChannelId == null || !memberChannelId.equals(botChannelId)) && botChannelId != null) {
            return new Maybe<>(
                    ConstantEmbeds.Companion.plainEmbed(
                            "Join the active voice channel",
                            "You must join <#" + botChannelId + "> to be able to add music!"
                    ));
        }

        if (raw() instanceof EmbedPlayEvent event) {
            return new Maybe<>(event.getSelectedSongId());
        }

        OptionMapping mapping = raw().getOption("query");
        if (mapping == null) {
            return new Maybe<>(
                    ConstantEmbeds.Companion.plainEmbed(
                            "The slash command you input was invalid",
                            "Please check your arguments and try again."
                    ));
        }

        return new Maybe<>(mapping.getAsString());
    }
}
