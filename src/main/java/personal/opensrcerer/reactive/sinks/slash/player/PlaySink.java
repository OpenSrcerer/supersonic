package personal.opensrcerer.reactive.sinks.slash.player;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.managers.AudioManager;
import personal.opensrcerer.audio.MusicPlayer;
import personal.opensrcerer.reactive.sinks.slash.SlashCommandSink;
import personal.opensrcerer.requests.RequestFormatter;
import personal.opensrcerer.requests.media.StreamRequest;

import java.util.Map;

public class PlaySink extends SlashCommandSink {

    public PlaySink(Permission... permissions) {
        super(permissions);
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public void receive(SlashCommandEvent event) {
        AudioManager manager = event.getGuild().getAudioManager();
        OptionMapping o = event.getOption("id");

        if (o == null) {
            event.reply("Missing ID parameter!").queue();
            return;
        }

        MusicPlayer.MUSIC_PLAYER.loadAndPlay(
                event.getTextChannel(),
                RequestFormatter.INSTANCE.getUrl(
                        new StreamRequest(
                                Map.of(
                                        "id", o.getAsString(),
                                        "maxBitRate", manager.getConnectedChannel().getBitrate() / 1000
                                )
                        ),
                        event.getGuild().getId()
                ).toString()
        );

        event.reply("Playing selected song.").queue();
    }
}
