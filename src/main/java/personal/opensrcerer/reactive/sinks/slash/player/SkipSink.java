package personal.opensrcerer.reactive.sinks.slash.player;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.services.audio.MusicPlayer;
import personal.opensrcerer.reactive.sinks.slash.SlashCommandSink;

public class SkipSink extends SlashCommandSink {

    public SkipSink(Permission... permissions) {
        super(permissions);
    }

    @Override
    public void onEvent(SlashCommandEvent event) {
        MusicPlayer.MUSIC_PLAYER.skipTrack(event.getGuild());
        event.reply("Skipped current track!").queue();
    }
}
