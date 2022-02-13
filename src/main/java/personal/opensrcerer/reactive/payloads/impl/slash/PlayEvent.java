package personal.opensrcerer.reactive.payloads.impl.slash;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.managers.AudioManager;
import personal.opensrcerer.messaging.constant.ConstantEmbeds;
import personal.opensrcerer.reactive.payloads.abstractions.SupersonicSlashCommandEvent;
import personal.opensrcerer.reactive.payloads.mock.EmbedPlayEvent;
import personal.opensrcerer.reactive.payloads.Maybe;

public class PlayEvent extends SupersonicSlashCommandEvent {

    private final String botChannelId;
    private final String memberChannelId;

    @SuppressWarnings("")
    public PlayEvent(SlashCommandEvent event) {
        super(event);
        AudioManager manager = event.getGuild().getAudioManager();
        botChannelId = (manager.isConnected()) ? manager.getConnectedChannel().getId() : null;
        memberChannelId = (event.getMember().getVoiceState().inVoiceChannel()) ?
                event.getMember().getVoiceState().getChannel().getId() : null;
    }

    @Override
    public Maybe<String> evaluate() {
        if (botChannelId == null && memberChannelId == null) {
            return new Maybe<>(
                    ConstantEmbeds.Companion.errorEmbed(
                            "Join a voice channel",
                            "You must join a voice channel to use this command!"
                    ));
        }

        if ((memberChannelId == null || !memberChannelId.equals(botChannelId)) && botChannelId != null) {
            return new Maybe<>(
                    ConstantEmbeds.Companion.errorEmbed(
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
                    ConstantEmbeds.Companion.errorEmbed(
                            "The slash command you input was invalid",
                            "Please check your arguments and try again."
                    ));
        }

        return new Maybe<>(mapping.getAsString());
    }
}
