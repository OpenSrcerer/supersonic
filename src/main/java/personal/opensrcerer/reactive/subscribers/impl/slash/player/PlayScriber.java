package personal.opensrcerer.reactive.subscribers.impl.slash.player;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.managers.AudioManager;
import personal.opensrcerer.aspect.annotations.BoundTo;
import personal.opensrcerer.aspect.annotations.Subscriber;
import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.aspect.mapping.EventMappingStrategy;
import personal.opensrcerer.reactive.payloads.impl.slash.PlayEvent;
import personal.opensrcerer.reactive.subscribers.abstractions.SlashCommandSuperscriber;
import personal.opensrcerer.requests.RequestFormatter;
import personal.opensrcerer.requests.media.StreamRequest;
import personal.opensrcerer.services.audio.MusicPlayer;

import java.util.Map;

@Subscriber(typeToHandle = SlashCommandEvent.class,
        strategy = EventMappingStrategy.SLASHCOMMAND_TO_PLAY)
@BoundTo(SlashCommand.PLAY)
public class PlayScriber extends SlashCommandSuperscriber<PlayEvent> {
    @Override
    public void onEvent(PlayEvent boxed) {
        SlashCommandEvent event = boxed.raw();
        AudioManager manager = event.getGuild().getAudioManager();
        OptionMapping o = event.getOption("id");
        String botChannelId = (manager.isConnected()) ? manager.getConnectedChannel().getId() : null;
        String memberChannelId = (event.getMember().getVoiceState().inVoiceChannel()) ?
                event.getMember().getVoiceState().getChannel().getId() : null;

        if (o == null) {
            event.reply("Missing ID parameter!").queue();
            return;
        }

        if (botChannelId == null && memberChannelId == null) {
            event.reply("You must join a voice channel to use this command!").queue();
            return;
        }

        if ((memberChannelId == null || !memberChannelId.equals(botChannelId)) && botChannelId != null) {
            event.reply("You must join <#" + botChannelId + "> to be able to add music!").queue();
            return;
        }

        if (botChannelId == null) {
            manager.openAudioConnection(event.getGuild().getVoiceChannelById(memberChannelId));
        }

        MusicPlayer.MUSIC_PLAYER.loadAndPlay(
                event.getTextChannel(),
                RequestFormatter.INSTANCE.getUrl(
                        new StreamRequest(
                                Map.of(
                                        "id", o.getAsString(),
                                        "maxBitRate", 128
                                )
                        ),
                        event.getGuild().getId()
                ).toString()
        );

        event.reply("Playing selected song.").queue();
    }
}
