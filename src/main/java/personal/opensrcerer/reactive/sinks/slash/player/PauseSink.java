package personal.opensrcerer.reactive.sinks.slash.player;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import personal.opensrcerer.audio.MusicPlayer;
import personal.opensrcerer.reactive.sinks.slash.SlashCommandSink;

public class PauseSink extends SlashCommandSink {
    public PauseSink(Permission... permissions) {
        super(permissions);
    }

    @Override
    public void receive(SlashCommandEvent event) {
        MusicPlayer.MUSIC_PLAYER.pause(event.getGuild());
        event.reply("Player paused!").queue();
    }
}
