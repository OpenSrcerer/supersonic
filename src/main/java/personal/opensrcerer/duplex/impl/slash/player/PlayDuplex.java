package personal.opensrcerer.duplex.impl.slash.player;

import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.managers.AudioManager;
import personal.opensrcerer.aspect.annotations.MappingStrategy;
import personal.opensrcerer.aspect.annotations.PostDuplex;
import personal.opensrcerer.config.SlashCommand;
import personal.opensrcerer.duplex.abstractions.GenericSlashCommandDuplex;
import personal.opensrcerer.duplex.payloads.impl.PlayEvent;
import personal.opensrcerer.duplex.payloads.EventMappingStrategy;
import personal.opensrcerer.requests.RequestFormatter;
import personal.opensrcerer.requests.media.StreamRequest;
import personal.opensrcerer.services.audio.MusicPlayer;

import java.util.Map;

@PostDuplex
@MappingStrategy(EventMappingStrategy.SLASHCOMMANDEVENT_TO_PLAYEVENT)
public class PlayDuplex extends GenericSlashCommandDuplex<PlayEvent> {
    public PlayDuplex() {
        super(SlashCommand.PLAY.getName());
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public void onEvent(PlayEvent playEvent) {


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
